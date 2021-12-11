package com.example.hacknplanstat2.model

import kotlinx.serialization.Serializable

@Serializable
data class Sprint(val boardId : Int, val name : String, val isDefault : Boolean) {

}
