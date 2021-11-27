package com.example.hacknplanstat2.model

import android.graphics.Color as Color
import androidx.compose.ui.graphics.Color as ComposeColor
import kotlinx.serialization.Serializable

@Serializable
data class RawCategory(var categoryId: Int, var name: String, var color : String){

    fun toCategory() : Category{
        return Category(categoryId, name, ComposeColor(Color.parseColor(color)))
    }
}

class Category(val categoryId: Int, val name: String, val  Color: ComposeColor) {

    fun getAverageCompletionTime(size : TaskSize) : Float{
        return size.size.toFloat();
    }

    fun getTotalTasks(size : TaskSize) : Int{
        return size.size.toInt();
    }

}