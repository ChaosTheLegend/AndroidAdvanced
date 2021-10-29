package com.example.lab1todolist.views

import android.graphics.Paint
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1todolist.DependencyInjectorImpl
import com.example.lab1todolist.R
import com.example.lab1todolist.TaskAdapter
import com.example.lab1todolist.contracts.MainContract
import com.example.lab1todolist.models.Task
import com.example.lab1todolist.presenters.MainPresenter
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.task_layout.view.*


class MainView : RecyclerView.Adapter<MainView.TaskViewHolder>() , MainContract.MainView {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var presenter: MainContract.MainPresenter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        preInit()

        setPresenter(MainPresenter(this, DependencyInjectorImpl()))
        presenter.onViewCreated()


        postInit()


        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var currentTask = presenter.loadTasks()[position]
        holder.itemView.apply {
            tvTaskTitle.text = currentTask.title;
            cbTaskDone.isChecked = currentTask.isChecked;
            toggleStrikeThrough(tvTaskTitle, currentTask.isChecked)
            cbTaskDone.setOnCheckedChangeListener{
                _, isChecked ->
                presenter.onItemCheckedChanged(position, isChecked)
            }
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG;
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv();
        }
    }

    override fun getItemCount(): Int {
        return presenter.loadTasks().size
    }


    override fun setPresenter(presenter: MainContract.MainPresenter) {
        this.presenter = presenter;
    }



    private fun preInit(){

    }

    private fun postInit(){

    }


    override fun onDestroy() {
        presenter.onDestroy()
    }

    override fun updateTasks(tasks: MutableList<Task>) {
        notifyDataSetChanged()
    }




}