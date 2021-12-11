package com.example.hacknplanstat2.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.model.Project
import com.example.hacknplanstat2.ui.theme.*
import com.example.hacknplanstat2.viewModel.ProjectsViewModel
import com.example.hacknplanstat2.tests.DummyViewModels.ProjectVMTest

class ProjectsView(private var viewModel: ProjectsViewModel) : ComposableView {


    private val isLoading = mutableStateOf(false)
    private lateinit var navController : NavController


    init {
        isLoading.value = true
        viewModel.onProjectsLoaded += {
            isLoading.value = false
        }

        viewModel.onProjectsOpened += {
            isLoading.value = false
            navController.navigate("projectMain")
        }

        viewModel.loadProjects()


    }

    @Composable
    override fun View(navController : NavController) {
        this.navController = navController

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

            Column {
                Header()
                Body()
            }
        }
    }


    @Composable()
    fun Header(){


        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()){
            Text(style = h3, text = "Welcome ${viewModel.username}", textAlign = TextAlign.Center)
        }

    }

    @Composable
    fun Body(){
        if(isLoading.value){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(Modifier
                    .size(80.dp))
            }
        }
        else{
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                items(viewModel.projects){
                        project -> ProjectView(project = project)
                }
            }
        }
    }
    
    @Composable
    fun ProjectView(project: Project){
        Button(onClick = {
            isLoading.value = true
            viewModel.openProject(project) },
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .fillMaxWidth()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp)) {
                Text(text = project.name, modifier = Modifier.padding(vertical = 4.dp), style = h4,
                    overflow = TextOverflow.Ellipsis, maxLines = 1)
                Text(text = project.description,
                    overflow = TextOverflow.Ellipsis, maxLines = 4,
                    modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Preview("Projects page")
@Composable
fun TestProjects(){
    ProjectsView(ProjectVMTest()).View(rememberNavController());
}
