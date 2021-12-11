package com.example.hacknplanstat2.repository

import androidx.compose.ui.graphics.Color
import com.example.hacknplanstat2.model.*
import org.koin.core.component.KoinComponent

class DummyRepository : Repository, KoinComponent {
    override val user: User
        get() = User(0, "test", email = "test", name = "test")

    override suspend fun checkApiKey(apiKey: String): Boolean {
        return apiKey == "goodKey"
    }

    override suspend fun getProjects(): List<Project> {
        return listOf(
            Project(0, "test1", ""),
            Project(1, "test2", ""),
            Project(2, "test3", ""),
            Project(3, "test4", ""),
        )
    }

    override var project: Project
        get() = Project(1, "test", description = "")
        set(value) {}
    override var sprint: Sprint
        get() = TODO("Not yet implemented")
        set(value) {}


    override suspend fun openProject(projectId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getCategories(): List<Category> {
        return listOf(
            Category(0, "Category 1", Color.Red),
            Category(1, "Category 2", Color.Yellow),
            Category(2, "Category 3", Color.Green),
            Category(3, "Category 4", Color.Blue),
        )
    }

    override suspend fun getDefaultSprint(): Sprint {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkItemsByCategory(category: Category): Map<TaskSize, List<Task>> {
        if(category.categoryId == 0) return mapOf(Pair(TaskSize.S, listOf(Task(0, 5f, emptyList()))))
        if(category.categoryId == 1) return mapOf(
            Pair(TaskSize.S, listOf(Task(1, 1f, emptyList()))),
            Pair(TaskSize.M, listOf(Task(2, 2f, emptyList()))),
            Pair(TaskSize.L, listOf(Task(3, 1f, emptyList()))))
        return mapOf(
            Pair(TaskSize.S, listOf(Task(4, 1f, emptyList()))),
            Pair(TaskSize.XL, listOf(Task(5, 20f, emptyList()))))
    }

    override suspend fun getSprintMetrics(): List<SprintMetrics> {
        TODO("Not yet implemented")
    }

    override suspend fun getSprintMetrics(category: Category): SprintMetrics {
        TODO("Not yet implemented")
    }

}
