package com.example.hacknplanstat2.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.repository.Repository
import com.example.hacknplanstat2.util.Event
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProjectsViewModelImpl : ViewModel(), ProjectsViewModel, KoinComponent{

    private val repository : Repository by inject()

    override fun openProject(project: Project) {
        viewModelScope.launch(){
            repository.openProject(project.id)
            onProjectsOpened.invoke(true)
        }
    }

    override fun loadProjects() {
        viewModelScope.launch{
            projects = repository.getProjects()
            onProjectsLoaded.invoke(true)
        }

    }

    override var onProjectsLoaded: Event<Boolean> = Event()
    override var onProjectsOpened: Event<Boolean> = Event()


    override var username: String = repository.user.username;
    override var projects: List<Project> = listOf()
}