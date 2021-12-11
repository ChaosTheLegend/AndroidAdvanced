package com.example.hacknplanstat2.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.viewModel.BoardViewModel

class BoardView(private var viewModel: BoardViewModel) : ComposableView {

    @Composable
    override fun View(navController: NavController) {

    }

}

@Preview
@Composable
fun BoardViewTest(){
    BoardView(BoardViewModelTestImpl()).View(rememberNavController());
}

class BoardViewModelTestImpl : BoardViewModel {

}
