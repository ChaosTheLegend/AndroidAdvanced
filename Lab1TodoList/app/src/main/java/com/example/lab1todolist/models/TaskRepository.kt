package com.example.lab1todolist.models

interface TaskRepository {
    var tasks : MutableList<Task>
}