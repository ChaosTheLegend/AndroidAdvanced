package com.example.hacknplanstat2.model

import kotlinx.serialization.Serializable

@Serializable
data class SprintMetrics(val category: RawCategory, val totalWorkItems : Int, val closedWorkItems : Int,
val estimatedCost : Float, val loggedCost : Float) {

    fun getProgress(): Float {
        return closedWorkItems.toFloat()/totalWorkItems.toFloat();
    }
}

@Serializable
data class MetricWrapper(val categories : List<SprintMetrics>)
