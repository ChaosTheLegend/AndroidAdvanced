package com.example.hacknplanstat2.viewModel

import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.util.Event

interface ProjectsViewModel {
    abstract fun openProject(project: Project);

    fun loadProjects()

    abstract var onProjectsLoaded: Event<Boolean>
    abstract var username : String
    abstract val projects : List<Project>
    var onProjectsOpened: Event<Boolean>
}