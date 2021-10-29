package com.example.lab1todolist.presenters

import com.example.lab1todolist.DependencyInjector
import com.example.lab1todolist.contracts.MainContract
import com.example.lab1todolist.models.Task
import com.example.lab1todolist.models.TaskRepository

class MainPresenter(view : MainContract.MainView,
                    dependencyInjector : DependencyInjector) : MainContract.MainPresenter{

    private val taskRepository: TaskRepository = dependencyInjector.taskRepository()
    private var view : MainContract.MainView? = view

    override fun onViewCreated() {
        TODO("Not yet implemented")
    }

    override fun loadTasks() : MutableList<Task>{
        return taskRepository.tasks
    }

    override fun onAddTask(taskName : String) {
        taskRepository.tasks.add(Task(taskName))
        //view?.displayTodoList()
    }

    override fun onDeleteTasksClicked() {
        taskRepository.tasks.removeAll { task -> task.isChecked }
    }

    override fun onItemCheckedChanged(id: Int, isChecked: Boolean) {
        taskRepository.tasks[id].isChecked = isChecked;
        view?.updateTasks(taskRepository.tasks)
    }

    override fun onDestroy() {
        view = null
    }
}