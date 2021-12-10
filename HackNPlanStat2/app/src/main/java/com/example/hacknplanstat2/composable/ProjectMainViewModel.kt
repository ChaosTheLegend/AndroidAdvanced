package com.example.hacknplanstat2.composable

import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.model.Sprint
import com.example.hacknplanstat2.model.Task

interface ProjectMainViewModel {
    abstract fun getProject(): Project
    abstract fun getSprint(): Sprint
    abstract fun getSprintProgress(): Float
    abstract fun getCompletedTasksCount(): Int
    abstract fun getTotalTaskCount(): Int
    abstract fun getCompletedHoursCount(): Float
    abstract fun getTotalHourCount(): Float
    abstract fun getCategories(): MutableList<Category>
    abstract fun getCategoryProgress(category: Category): Float

}
