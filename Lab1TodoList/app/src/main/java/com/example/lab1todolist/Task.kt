package com.example.lab1todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_layout.view.*

data class Task(
    val title: String,
    var isChecked: Boolean = false,
)

class TaskAdapter(
    private val tasks : MutableList<Task>
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addTask(task : Task){
        //Model
        tasks.add(task)

        //View
        notifyItemInserted(tasks.size - 1);
    }

    fun deleteDoneTasks(){
        //Model
        tasks.removeAll { task -> task.isChecked}

        //View
        notifyDataSetChanged()
    }



    //View
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG;
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv();
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position];
        holder.itemView.apply {
            tvTaskTitle.text = currentTask.title;
            cbTaskDone.isChecked = currentTask.isChecked;
            toggleStrikeThrough(tvTaskTitle, currentTask.isChecked)
            cbTaskDone.setOnCheckedChangeListener { _, isChecked ->
                currentTask.isChecked = !currentTask.isChecked;
                toggleStrikeThrough(tvTaskTitle, currentTask.isChecked)  }

        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}
