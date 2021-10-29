package com.example.lab1todolist.contracts

import com.example.lab1todolist.BasePresenter
import com.example.lab1todolist.BaseView
import com.example.lab1todolist.models.Task

interface MainContract {
    interface MainPresenter : BasePresenter{
        fun onAddTask(taskName : String)
        fun onDeleteTasksClicked()
        fun onItemCheckedChanged(id : Int, isChecked : Boolean)
        fun loadTasks(): MutableList<Task>
    }

    interface MainView : BaseView<MainPresenter>{
        fun updateTasks(tasks : MutableList<Task>)
        fun onDestroy()
    }
}