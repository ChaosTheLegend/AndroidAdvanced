package com.example.hacknplanstat2.model

import kotlinx.serialization.Serializable

@Serializable
data class Tag(var name: String){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}

@Serializable
data class Task(var workItemId : Int, var loggedCost : Float, var tags : List<Tag>) {

}

@Serializable
data class TaskPage(var items : List<Task>)