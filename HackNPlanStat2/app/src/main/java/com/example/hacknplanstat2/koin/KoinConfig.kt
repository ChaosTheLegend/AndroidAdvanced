package com.example.hacknplanstat2.koin

import com.example.hacknplanstat2.repository.DummyRepository
import com.example.hacknplanstat2.repository.OnlineRepository
import com.example.hacknplanstat2.repository.Repository
import com.example.hacknplanstat2.viewModel.*
import org.koin.dsl.module

var koinModules = module{
    single { LoginViewModelImpl() as LoginViewModel }
    single { OnlineRepository() as Repository}
    single { ProjectsViewModelImpl() as ProjectsViewModel}
    single { CategoryMetricsViewModelImpl() as CategoryMetricsViewModel }
    single { ProjectMainViewModelImpl() as ProjectMainViewModel}
}

var testKoinModules = module{
    single { LoginViewModelImpl() as LoginViewModel }
    single { DummyRepository() as Repository}
    single { ProjectsViewModelImpl() as ProjectsViewModel}
    single { CategoryMetricsViewModelImpl() as CategoryMetricsViewModel }
    single { ProjectMainViewModelImpl() as ProjectMainViewModel}
}