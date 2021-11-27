package com.example.hacknplanstatistics.koin

import com.example.hacknplanstatistics.ui.main.MainFragment
import com.example.hacknplanstatistics.ui.main.LoginViewModelImpl
import org.koin.dsl.module

var koinModules = module{
    single { LoginViewModelImpl() }
    factory { MainFragment(get<LoginViewModelImpl>()) }
}
