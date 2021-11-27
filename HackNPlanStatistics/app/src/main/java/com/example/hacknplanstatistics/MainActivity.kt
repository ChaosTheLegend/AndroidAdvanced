package com.example.hacknplanstatistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.hacknplanstatistics.composable.LoginView
import com.example.hacknplanstatistics.koin.koinModules
import com.example.hacknplanstatistics.ui.main.LoginViewModelImpl
import org.koin.android.ext.android.get
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : AppCompatActivity() {

    init{
        startKoin{
            modules(koinModules)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            LoginView(get<LoginViewModelImpl>())
        }
        //setContentView(R.layout.main_activity)

        //val ft = supportFragmentManager.beginTransaction()
        //ft.replace(R.id.container, get<MainFragment>())
        //ft.commit()

    }






}