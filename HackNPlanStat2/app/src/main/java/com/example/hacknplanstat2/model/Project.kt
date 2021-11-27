package com.example.hacknplanstat2.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(var id : Int,var name : String, var description : String){}
