package com.example.lab1todolist

interface BaseView<T : BasePresenter> {

    fun setPresenter(presenter: T)

}
