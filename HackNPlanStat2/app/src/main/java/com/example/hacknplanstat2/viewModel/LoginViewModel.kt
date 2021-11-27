package com.example.hacknplanstat2.viewModel

import com.example.hacknplanstat2.util.Event

interface LoginViewModel {
    fun Login()

    abstract var apiKey: String
    abstract val onLoginFinish: Event<Boolean>
}
