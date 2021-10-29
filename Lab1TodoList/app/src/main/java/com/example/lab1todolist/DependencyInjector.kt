package com.example.lab1todolist

import com.example.lab1todolist.models.TaskRepository

interface DependencyInjector {
    fun taskRepository() : TaskRepository

}