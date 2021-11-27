package com.example.hacknplanstat2.repository

import com.example.hacknplanstat2.model.*

interface Repository {

    val user : User

    suspend fun checkApiKey(apiKey: String): Boolean
    suspend fun getProjects() : List<Project>


    var project: Project
    suspend fun openProject(projectId: Int)
    suspend fun getCategories() : List<Category>
    suspend fun getWorkItemsByCategory(category: Category): Map<TaskSize, List<Task>>
}
