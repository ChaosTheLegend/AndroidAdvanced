package com.example.hacknplanstat2.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.viewModel.LoginViewModelImpl
import com.example.hacknplanstat2.ui.theme.h1
import com.example.hacknplanstat2.ui.theme.h6
import com.example.hacknplanstat2.viewModel.LoginViewModel

class LoginView(private var viewModel: LoginViewModel) : ComposableView{


    init{
        viewModel.onLoginFinish += {
            isLoading.value = false
            showLoginError.value = !it
            println("show error ${showLoginError.value}")
            if(it){
                navController.navigate("projects")
            }
        }
    }

    private val showLoginError = mutableStateOf(false)
    private val isLoading = mutableStateOf(false)
    private lateinit var navController : NavController

    @Composable
    override fun View(navController : NavController){

        this.navController = navController

        if(showLoginError.value) showLoginError()
        Header()
        Body()
    }

    @Composable
    fun showLoginError(){
        AlertDialog(
            onDismissRequest = {
                showLoginError.value = false
            },
            title = {
                Text(text = "Login Error")
            },
            text = {
                Text("Api key you provided is incorrect")
            },
            buttons = {

            }
        )
    }


    @Composable
    fun Body(){
        val apiKeyValue = remember{ mutableStateOf("")}
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
        ) {
            Text(text = "Sign in", style = h6)
            OutlinedTextField(
                value = apiKeyValue.value,
                onValueChange = {
                    apiKeyValue.value = it;
                    viewModel.apiKey = apiKeyValue.value},
                label = {Text(text = "api key")},
                placeholder = {Text(text = "api key")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)

            )

            Spacer(modifier = Modifier.padding(6.dp))

            Button(onClick = {
                isLoading.value = true
                viewModel.Login()
                              },
                enabled = apiKeyValue.value.isNotEmpty() and !isLoading.value,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(58.dp)) {
                if(isLoading.value) CircularProgressIndicator()
                else Text(text = "Log in", style = h6)
            }
        }

    }

    @Composable()
    fun Header(){


        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter){
            Text(style = h1, text = "HackNPlan Statistics", textAlign = TextAlign.Center)
        }

    }
}


@Preview("Login page")
@Composable
fun TestLogin(){
    LoginView(LoginViewModelImpl()).View(rememberNavController())
}



