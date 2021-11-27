package com.example.hacknplanstat2.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.TaskSize
import com.example.hacknplanstat2.util.Event

interface CategoryMetricsViewModel {
    abstract fun getCategories()


    abstract var categories: List<Category>
    abstract val projectName: String


    abstract var loadedCategories : MutableState<Int>;
    abstract var totalCategories : MutableState<Int>;

    val onCategoriesLoaded: Event<Boolean>
    fun getCount(category: Category, taskSize: TaskSize): Int
    fun getAverage(category: Category, taskSize: TaskSize): Float
}