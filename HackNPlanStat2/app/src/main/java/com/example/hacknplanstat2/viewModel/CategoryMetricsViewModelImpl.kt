package com.example.hacknplanstat2.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.Task
import com.example.hacknplanstat2.model.TaskSize
import com.example.hacknplanstat2.repository.Repository
import com.example.hacknplanstat2.util.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoryMetricsViewModelImpl : ViewModel(), CategoryMetricsViewModel, KoinComponent {

    private val repository : Repository by inject()
    private var mapHasBeenInitialized : Boolean = false
    private var lastProjectId : Int = 0;
    private var hourRateMap : Map<Category, Map<TaskSize, Float>> = mapOf()
    private var countMap : Map<Category, Map<TaskSize, Int>> = mapOf()

    override var loadedCategories = mutableStateOf(0)
    override var totalCategories = mutableStateOf(100)

    override fun getCategories(){

        if(mapHasBeenInitialized && lastProjectId == repository.project.id){
            onCategoriesLoaded.invoke(true)
            return
        }

        viewModelScope.launch {
            loadedCategories.value = 0
            totalCategories.value = 100
            categories = repository.getCategories()

            totalCategories.value = categories.size

            delay(600)

            categories.forEach{ category ->
                val itemsMap = repository.getWorkItemsByCategory(category)
                val avr = getWorkItemsAverageRate(itemsMap)
                val count = getWorkItemsCount(itemsMap)

                hourRateMap = hourRateMap + Pair(category, avr)
                countMap = countMap + Pair(category, count)

                //Required not to exceed request quota
                delay(600)

                loadedCategories.value++
            }
            mapHasBeenInitialized = true
            lastProjectId = repository.project.id
            onCategoriesLoaded.invoke(true)
        }
    }

    fun getWorkItemsCount(map : Map<TaskSize, List<Task>>) : Map<TaskSize, Int>{
        return map.mapValues { it.value.count() }
    }

    fun getWorkItemsAverageRate(map : Map<TaskSize, List<Task>>) : Map<TaskSize, Float>{
        return map.mapValues { it.value.map { task ->  task.loggedCost }.average().toFloat() }
    }

    override fun getCount(category : Category, taskSize: TaskSize) : Int{
        return countMap[category]?.get(taskSize)!!
    }

    override fun getAverage(category : Category, taskSize: TaskSize) : Float{
        return hourRateMap[category]?.get(taskSize)!!
    }

    override var categories: List<Category> = listOf()
    override val onCategoriesLoaded : Event<Boolean> = Event()

    override val projectName: String = ""

}
