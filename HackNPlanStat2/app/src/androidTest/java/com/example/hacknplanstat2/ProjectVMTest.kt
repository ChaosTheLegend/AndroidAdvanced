package com.example.hacknplanstat2

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hacknplanstat2.koin.testKoinModules
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.viewModel.ProjectsViewModelImpl
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProjectVMTest {

    fun testPrep() {
        GlobalContext.stopKoin()
        GlobalContext.startKoin {
            modules(testKoinModules)
        }
    }

    @Test
    fun getProjectsTest(){
        testPrep();

        var vm = ProjectsViewModelImpl();
        var expect = listOf(
            Project(0, "test1", ""),
            Project(1, "test2", ""),
            Project(2, "test3", ""),
            Project(3, "test4", ""),
        )

        vm.onProjectsLoaded +={
            assertEquals(expect,vm.projects)
        }

        vm.loadProjects();
    }
}