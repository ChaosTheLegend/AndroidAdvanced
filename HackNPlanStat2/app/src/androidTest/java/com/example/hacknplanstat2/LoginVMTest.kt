package com.example.hacknplanstat2

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hacknplanstat2.koin.koinModules
import com.example.hacknplanstat2.koin.testKoinModules
import com.example.hacknplanstat2.viewModel.LoginViewModelImpl
import junit.framework.TestCase.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext


@RunWith(AndroidJUnit4::class)
class LoginVMTest {

    @Test
    fun goodLoginTest(){

        GlobalContext.stopKoin()
        GlobalContext.startKoin {
            modules(testKoinModules)
        }

        var vm = LoginViewModelImpl()
        vm.apiKey = "goodKey"
        vm.onLoginFinish += {
            assertEquals(true, it)
        }
        vm.Login()
    }

    @Test
    fun badLoginTest(){

        GlobalContext.stopKoin()
        GlobalContext.startKoin {
            modules(testKoinModules)
        }

        var vm = LoginViewModelImpl()
        vm.apiKey = "badKey"
        vm.onLoginFinish += {
            assertEquals(false, it)
        }
        vm.Login()
    }
}