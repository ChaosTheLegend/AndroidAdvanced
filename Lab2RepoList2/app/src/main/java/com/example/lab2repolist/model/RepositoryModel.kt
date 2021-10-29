package com.example.lab2repolist
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File
import java.lang.reflect.Method

@Serializable
data class Repository(var name : String, var full_name : String){
}

class UserRepositories(var username : String, var userUrl : String){
    var repos : List<Repository> = listOf()
}