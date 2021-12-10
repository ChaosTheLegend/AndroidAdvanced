package com.example.hacknplanstat2.tests.DummyViewModels

import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.util.Event
import com.example.hacknplanstat2.viewModel.ProjectsViewModel

class ProjectVMTest : ProjectsViewModel {
    override fun openProject(project: Project) {

    }

    override fun loadProjects() {
        {

        }
        onProjectsLoaded.invoke(true)
    }

    override var onProjectsLoaded: Event<Boolean> = Event()

    val loadingProjects: Boolean = false

    override var username: String = "Test user"
    override val projects: List<Project>
        get() = listOf(Project(0,"Project1", "project to test project view"),
            Project(0,"Project2", "another project to test project view"),
            Project(0,"Project3", "project with lots of hentai pics"),
            Project(0,"Project4", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Another Project", "project just for fun"),
            Project(0,"Project with a very long name to test text wrapping, yes this is a very long name, long enough to break this program if it's not designed properly",
                "project just for fun")
        )
    override var onProjectsOpened: Event<Boolean> = Event()
}