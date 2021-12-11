package com.example.hacknplanstat2

import androidx.compose.ui.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hacknplanstat2.koin.testKoinModules
import com.example.hacknplanstat2.model.Category
import com.example.hacknplanstat2.model.Task
import com.example.hacknplanstat2.model.TaskSize
import com.example.hacknplanstat2.viewModel.CategoryMetricsViewModel
import com.example.hacknplanstat2.viewModel.CategoryMetricsViewModelImpl
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class CategoryVMTest {

    fun testPrep() {
        GlobalContext.stopKoin()
        GlobalContext.startKoin {
            modules(testKoinModules)
        }
    }

        @Test
    fun getCategoriesTest(){

        GlobalContext.stopKoin()
        GlobalContext.startKoin {
            modules(testKoinModules)
        }

        var vm = CategoryMetricsViewModelImpl();

        var expect = listOf(
            Category(0, "Category 1", Color.Red),
            Category(1, "Category 2", Color.Yellow),
            Category(2, "Category 3", Color.Green),
            Category(3, "Category 4", Color.Blue),
        )

        vm.onCategoriesLoaded += {
            assertEquals(expect, vm.categories)
        }
    }

    @Test
    fun metricTest(){
        testPrep();
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {

            var category = Category(1, "test", Color.Red);
            var avr = vm.getAverage(category, TaskSize.M);
            assertEquals(1f, avr);
        }
        vm.getCategories();
    }

    @Test
    fun metricTest2(){
        testPrep();
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {

            var category = Category(0, "test", Color.Red);
            var avr = vm.getAverage(category, TaskSize.S);
            assertEquals(5f, avr);
        }
        vm.getCategories();
    }

    @Test
    fun metricTest3(){
        testPrep();
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {

            var category = Category(5, "test", Color.Red);
            var avr = vm.getAverage(category, TaskSize.XL);
            assertEquals(20f, avr);
        }
        vm.getCategories();
    }

    @Test
    fun categoryIndexTest(){
        testPrep()
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {
            assertCategories(vm)
        }
        vm.getCategories();
    }

    @Test
    fun categoryIndexTest2(){
        testPrep()
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {
            assertCategories2(vm)
        }
        vm.getCategories();
    }

    @Test
    fun categoryIndexTest3(){
        testPrep()
        var vm = CategoryMetricsViewModelImpl();
        //var expect = mapOf(Pair(TaskSize.S, listOf(Task(0, 1f, emptyList()))));
        vm.onCategoriesLoaded += {
            assertCategories3(vm)
        }
        vm.getCategories();
    }

    fun assertCategories2(vm: CategoryMetricsViewModel){
        var category = Category(1, "test", Color.Red);

        var count = vm.getCount(category, TaskSize.L);
        assertEquals(1, count);
    }

    fun assertCategories3(vm: CategoryMetricsViewModel){
        var category = Category(3, "test", Color.Red);

        var count = vm.getCount(category, TaskSize.XL);
        assertEquals(1, count);
    }

    fun assertCategories(vm: CategoryMetricsViewModel){
        var category = Category(0, "test", Color.Red);

        var count = vm.getCount(category, TaskSize.S);
        assertEquals(1, count);
    }

}