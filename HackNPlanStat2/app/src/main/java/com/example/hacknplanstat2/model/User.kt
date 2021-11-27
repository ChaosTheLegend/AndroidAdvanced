package com.example.hacknplanstat2.model

import kotlinx.serialization.Serializable


@Serializable
data class User(var id : Int, var username : String, var email : String, var name : String)