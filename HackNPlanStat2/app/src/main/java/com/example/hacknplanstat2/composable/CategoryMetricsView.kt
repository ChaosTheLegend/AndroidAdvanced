package com.example.hacknplanstat2.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.TaskSize
import com.example.hacknplanstat2.ui.theme.h3
import com.example.hacknplanstat2.ui.theme.h4
import com.example.hacknplanstat2.viewModel.CategoryMetricsViewModel
import com.example.hacknplanstat2.viewModel.test.CategoryVMtest

class CategoryMetricsView(var viewModel : CategoryMetricsViewModel) : ComposableView {

    private val categoriesLoading = mutableStateOf(false)
    private val cellHeight = 70.dp;

    init{
        categoriesLoading.value = true
        viewModel.getCategories()
        viewModel.onCategoriesLoaded += {
            categoriesLoading.value = false
        }
    }


    @Composable
    override fun View(navController : NavController) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {

            Column {
                Header()
                Body()
            }
        }
    }

    @Composable
    fun Header(){
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()){
            Text(style = h3, text = "${viewModel.projectName} Categories", textAlign = TextAlign.Center)
        }
    }

    @Composable
    fun Body(){

        if(categoriesLoading.value){

            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()) {
                
                Text(text = "Indexing Categories...", style = h4)

                CircularProgressIndicator(progress = (viewModel.loadedCategories.value.toFloat() / viewModel.totalCategories.value.toFloat()).toFloat(),
                    modifier = Modifier
                    .size(80.dp))
            }

            return
        }

        // Each cell of a column must have the same weight.
        val column1Weight = .3f // 30%

        val sizeColumnWeight = (1 - column1Weight)/5 //70/5%
        val column2Weight = .7f // 70%

        val tableData = (1..100).mapIndexed { index, item ->
            index to "Item $index"
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            // Here is the header
            item {
                Row(Modifier.background(Color.Gray)) {
                    TableCell(text = "Category", weight = column1Weight)

                    TableCell(text = "XS", weight = sizeColumnWeight)
                    TableCell(text = "S", weight = sizeColumnWeight)
                    TableCell(text = "M", weight = sizeColumnWeight)
                    TableCell(text = "L", weight = sizeColumnWeight)
                    TableCell(text = "XL", weight = sizeColumnWeight)
                }
            }
            // Here are all the lines of your table.
            items(viewModel.categories){
                val category = it
                Row(Modifier.fillMaxWidth()) {
                    TableCell(category = category, weight = column1Weight)


                    TableCell(category = category, taskSize = TaskSize.XS, weight = sizeColumnWeight)
                    TableCell(category = category, taskSize = TaskSize.S, weight = sizeColumnWeight)
                    TableCell(category = category, taskSize = TaskSize.M, weight = sizeColumnWeight)
                    TableCell(category = category, taskSize = TaskSize.L, weight = sizeColumnWeight)
                    TableCell(category = category, taskSize = TaskSize.XL, weight = sizeColumnWeight)
                }
            }
        }
    }

    @Composable
    fun RowScope.TableCell(
        category : Category,
        taskSize : TaskSize,
        weight : Float
    ) {
        Box(
            Modifier
                .height(cellHeight)
                .weight(weight)
                .border(1.dp, Color.Black))

        {

            Column() {
                var avr = viewModel.getAverage(category, taskSize);
                if(!avr.isNaN()) {
                    Text(
                        text = "${avr.toString()}h",
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)

                    )

                    Text(
                        text = "(${viewModel.getCount(category, taskSize).toString()})",
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)

                    )
                }
            }
        }
    }

    @Composable
    fun RowScope.TableCell(
        category : Category,
        weight : Float
    ) {
        Box(
            Modifier
                .height(cellHeight)
                .weight(weight)
                .background(color = category.Color)
                .border(1.dp, Color.Black))
            {

            Text(
                text = category.name,
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            )
        }
    }

    @Composable
    fun RowScope.TableCell(
        text: String,
        weight: Float
    ) {
        Text(
            text = text,
            Modifier

                .height(cellHeight)
                .border(1.dp, Color.Black)
                .weight(weight)
                .padding(8.dp)
        )
    }
}

@Preview("Category view")
@Composable
fun testCompose(){
    CategoryMetricsView(CategoryVMtest()).View(rememberNavController())
}