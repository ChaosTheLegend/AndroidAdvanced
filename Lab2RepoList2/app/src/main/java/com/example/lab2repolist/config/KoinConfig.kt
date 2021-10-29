package com.example.lab2repolist

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.databinding.BindingAdapter
import com.example.lab2repolist.adapter.RepoAdapter
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.dsl.module


var koinModules = module{
    single { RepositoryViewModel() }
    factory { MainActivity() }
}
