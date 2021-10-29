package com.example.lab2repolist

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.NoCache
import com.android.volley.toolbox.StringRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.Collections.list

class RepositoryViewModel() : ViewModel(), Observable {


    @Bindable
    private val _repositories = MutableLiveData<List<Repository>>()
    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    @Bindable
    var user = MutableLiveData<String>()

    init {
        user.value = "test"

    }


    val userRepositories : MutableLiveData<List<Repository>>
        get() = _repositories


    fun loadRepos(){
        val user = user.value;
        user?.let{
            loadRepositories(it)
        }
    }

    val format = Json{ignoreUnknownKeys = true}
    // Set up the network to use HttpURLConnection as the HTTP client.
    val network = BasicNetwork(HurlStack())
    val cache = NoCache()
    val requestQueue = RequestQueue(cache ,network).apply {
        start()
    }

    private fun loadRepositories(user: String){
        val url = "https://api.github.com/users/$user/repos"
        val queue = requestQueue
        val request = StringRequest(Request.Method.GET, url, {
                response ->
            var list = format.decodeFromString<List<Repository>>(response)
            println(list.size)
            for (item in list){
                println(item.name)
            }
            _repositories.value = list
            notifyChange()
        }, {
            println("An error has occurred")
            _repositories.postValue(mutableListOf(Repository("An", "An"), Repository("Error", "Error"), Repository("Has", "Has"), Repository("Occured", "Occurred")))
            notifyChange()
        })

        queue.add(request)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }


    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}