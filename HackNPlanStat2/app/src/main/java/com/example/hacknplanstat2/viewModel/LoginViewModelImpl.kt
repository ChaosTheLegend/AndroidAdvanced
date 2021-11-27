package com.example.hacknplanstat2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacknplanstat2.repository.Repository
import com.example.hacknplanstat2.util.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModelImpl : ViewModel(), LoginViewModel, KoinComponent {

    private val repository : Repository by inject()


    override fun Login(){
       viewModelScope.launch{
           println("launched coroutine")
           val key = repository.checkApiKey(apiKey)
           onLoginFinish.invoke(key)
           println("Got the response")
           println(key)
       }
    }

    override var apiKey: String = "";
    override val onLoginFinish: Event<Boolean> = Event()
}