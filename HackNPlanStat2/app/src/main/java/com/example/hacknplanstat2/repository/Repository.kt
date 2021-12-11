package com.example.hacknplanstat2.repository

import com.example.hacknplanstat2.model.*

interface Repository {

    val user : User

    suspend fun checkApiKey(apiKey: String): Boolean
    suspend fun getProjects() : List<Project>


    var project: Project
    var sprint : Sprint;
    suspend fun openProject(projectId: Int)
    suspend fun getCategories() : List<Category>
    suspend fun getDefaultSprint() : Sprint
    suspend fun getWorkItemsByCategory(category: Category): Map<TaskSize, List<Task>>
    suspend fun getSprintMetrics(): List<SprintMetrics>
    suspend fun getSprintMetrics(category: Category): SprintMetrics
}
