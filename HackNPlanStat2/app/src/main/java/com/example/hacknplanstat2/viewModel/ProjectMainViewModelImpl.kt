package com.example.hacknplanstat2.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.model.Sprint
import com.example.hacknplanstat2.model.SprintMetrics
import com.example.hacknplanstat2.repository.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class ProjectMainViewModelImpl : ViewModel(), ProjectMainViewModel, KoinComponent {

    private val repository : Repository by inject();
    private lateinit var sprint : Sprint;
    private lateinit var metrics : List<SprintMetrics>;
    private lateinit var categories : List<Category>;

    init {
        load()
    }

    override var isLoaded = mutableStateOf(false);



    override fun getProject(): Project  {
        return repository.project
    }

    override fun load(){

        isLoaded = mutableStateOf(false)
        viewModelScope.launch {
            isLoaded.value = false;

            sprint = repository.getDefaultSprint();
            metrics = repository.getSprintMetrics()
            categories = repository.getCategories()

            println("Loaded!")
            isLoaded.value = true
        }
    }

    override fun getSprint(): Sprint{
        return sprint;
    }

    override fun getSprintProgress(): Float{
        var total = 0;
        var closed = 0;
        for (metric in metrics) {
            total += metric.totalWorkItems
            closed += metric.closedWorkItems
        }

        return closed.toFloat() / total.toFloat()
    }

    override fun getCompletedTasksCount(): Int {

        var closed = 0;
        for (metric in metrics) {
            closed += metric.closedWorkItems
        }

        return closed
    }

    override fun getTotalTaskCount(): Int {

        var total = 0;
        for (metric in metrics) {
            total += metric.totalWorkItems
        }

        return total
    }

    override fun getCompletedHoursCount(): Float {

        var total = 0f;
        for (metric in metrics) {
            total += metric.loggedCost
        }

        return total
    }

    override fun getTotalHourCount(): Float {
        var total = 0f;
        for (metric in metrics) {
            total += metric.estimatedCost
        }

        return total
    }

    override fun getCategories(): List<Category> {

        return categories;
    }

    override fun getCategoryProgress(category: Category): Float {
        lateinit var output : SprintMetrics
        for (metric in metrics) {
            if(metric.category.categoryId == category.categoryId) {
                output = metric
                break;
            }
        }

        return output.getProgress()
    }
}