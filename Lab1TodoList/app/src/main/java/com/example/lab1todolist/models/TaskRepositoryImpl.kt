package com.example.lab1todolist.models

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.File

class TaskRepositoryImpl : TaskRepository {
    override var tasks : MutableList<Task> = loadTasksFromJson("jsonData/tasks.json")

    private fun parseTasksFromJson(json : String): MutableList<Task> {
        val jsonArray = JSONTokener(json).nextValue() as JSONArray
        val tasks : MutableList<Task> = mutableListOf()
        for (i in 0 until jsonArray.length()) {
            val title = jsonArray.getJSONObject(i).getString("title")
            val isChecked = jsonArray.getJSONObject(i).getBoolean("is_checked")
            tasks.add(Task(title, isChecked))
        }

        return tasks
    }

    private fun loadTasksFromJson(path : String): MutableList<Task>{
        var file = File(path).readText()
        return parseTasksFromJson(file)
    }


}

data class Task(
    val title: String,
    var isChecked: Boolean = false,
)
