package com.example.hacknplanstat2.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.tests.DummyViewModels.ProjectMainViewModelTestImpl
import com.example.hacknplanstat2.ui.theme.*
import com.example.hacknplanstat2.viewModel.ProjectMainViewModel

class ProjectMainView(private var viewModel : ProjectMainViewModel) : ComposableView{

    private lateinit var navController : NavController
    private val cellHeight = 50.dp;

    @Composable
    override fun View(navController: NavController) {
        this.navController = navController

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter){

            Column(horizontalAlignment = Alignment.CenterHorizontally){
                Header()
                if(viewModel.isLoaded.value) {
                    Body()
                }
                else{
                    Box() {
                        LoadingIndicator()
                    }
                }
            }

        }
    }

    @Composable
    fun LoadingIndicator(){
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp))

    }

    @Composable
    fun Header(){
        Text(style = h1, text = "${viewModel.getProject().name} Statistics", textAlign = TextAlign.Center)
    }

    @Composable
    fun Body(){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            DrawSprintInfo();

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                navController.navigate("categoryMetrics")
            }){
                Text(text = "View details", style = h5)
            }

            Spacer(modifier = Modifier.height(40.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)) {
                DrawCategoryMetrics();
            }
        }

    }

    @Composable
    fun DrawCategoryMetrics() {
        LazyColumn{
            items(viewModel.getCategories()){
                category -> DrawCategory(category)
            }
        }
    }

    @Composable
    fun DrawCategory(category: Category) {
        Row{
            Box(
                Modifier
                    .height(cellHeight)
                    .weight(0.35f)
                    .background(color = category.Color)
                    .border(1.dp, Color.Black))
            {

                Text(
                    style = h6,
                    text = category.name,
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)

                )
            }

            Box(
                modifier = Modifier
                    .height(cellHeight)
                    .weight(0.65f)
                    .fillMaxSize()
                    )

            {
                LinearProgressIndicator(
                    progress = viewModel.getCategoryProgress(category),
                    modifier = Modifier
                        .height(20.dp)
                        .padding(horizontal = 10.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }

    @Composable
    fun DrawSprintInfo(){
        Text(style = h3, text = viewModel.getSprint().name, textAlign = TextAlign.Center)
        Row {
            LinearProgressIndicator(progress = viewModel.getSprintProgress(),
            modifier = Modifier
                .height(15.dp)
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterVertically))
            Text(
                style = h6,
                text = "${viewModel.getSprintProgress() * 100}%",
                textAlign = TextAlign.Center
            )
        }
        Text(style = h5, text = "${viewModel.getCompletedTasksCount()}/${viewModel.getTotalTaskCount()} Tasks", textAlign = TextAlign.Center)
        Text(style = h5, text = "${viewModel.getCompletedHoursCount()}/${viewModel.getTotalHourCount()} Hours", textAlign = TextAlign.Center)
    }

}

@Preview
@Composable
fun TestProjectMainView(){
    ProjectMainView(ProjectMainViewModelTestImpl()).View(rememberNavController());
}

