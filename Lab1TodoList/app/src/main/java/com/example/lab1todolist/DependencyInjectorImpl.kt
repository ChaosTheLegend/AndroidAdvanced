package com.example.lab1todolist

import com.example.lab1todolist.models.TaskRepository
import com.example.lab1todolist.models.TaskRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun taskRepository(): TaskRepository {
        return TaskRepositoryImpl()
    }
}