package com.example.hacknplanstat2.viewModel.test

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.TaskSize
import com.example.hacknplanstat2.util.Event
import com.example.hacknplanstat2.viewModel.CategoryMetricsViewModel

class CategoryVMtest : CategoryMetricsViewModel{
    override fun getCategories() {
        }

    override var categories: List<Category> = listOf()

    override val projectName: String
        get() = "Test Project"
    override var loadedCategories: MutableState<Int> = mutableStateOf(1)
    override var totalCategories: MutableState<Int> = mutableStateOf(1)


    override val onCategoriesLoaded: Event<Boolean> = Event()
    override fun getCount(category: Category, taskSize: TaskSize): Int {
        TODO("Not yet implemented")
    }

    override fun getAverage(category: Category, taskSize: TaskSize): Float {
        TODO("Not yet implemented")
    }
}