package com.example.hacknplanstat2.composable

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

interface ComposableView {
    @Composable
    fun View(navController : NavController)
}
