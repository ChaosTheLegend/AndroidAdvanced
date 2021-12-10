package com.example.hacknplanstat2.tests.DummyViewModels

import androidx.compose.ui.graphics.Color
import com.example.hacknplanstat2.composable.ProjectMainViewModel
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.model.Sprint

class ProjectMainViewModelTestImpl : ProjectMainViewModel {
    override fun getProject(): Project {
        return Project(0, "TestProj", "some test proj")
    }

    override fun getSprint(): Sprint {
        return Sprint(0, "Test Sprint", true);
    }

    override fun getSprintProgress(): Float {
        return 0.7f;
    }

    override fun getCompletedTasksCount(): Int {
        return 70
    }

    override fun getTotalTaskCount(): Int {
        return 100
    }

    override fun getCompletedHoursCount(): Float {
        return 250.2f
    }

    override fun getTotalHourCount(): Float {
        return 300f
    }

    override fun getCategories(): MutableList<Category> {
        return mutableListOf(
            Category(0, "Test1", Color.Green),
            Category(1, "Test2", Color.Red),
            Category(2, "Test3", Color.Blue),
            Category(3, "Test4", Color.Yellow),
            )
    }

    override fun getCategoryProgress(category: Category): Float {
        return 0.6f
    }


}