package com.example.hacknplanstat2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hacknplanstat2.composable.*
import com.example.hacknplanstat2.koin.koinModules
import com.example.hacknplanstat2.viewModel.LoginViewModelImpl
import com.example.hacknplanstat2.ui.theme.HackNPlanStat2Theme
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.core.context.GlobalContext
import org.koin.android.ext.android.get


class MainActivity : ComponentActivity() {

    init{
        GlobalContext.startKoin {
            modules(koinModules)
        }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HackNPlanStat2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                        val navController = rememberNavController()

                        NavHost(navController = navController, startDestination = "login" ){
                            composable("login") {
                                LoginView(get()).View(navController)
                            }
                            composable("projects") {
                                ProjectsView(get()).View(navController)
                            }
                            composable("projectMain") {
                                ProjectMainView(get()).View(navController)
                            }
                            composable("categoryMetrics") {
                                CategoryMetricsView(get()).View(navController)
                            }
                        }
                }
            }
        }
    }
}