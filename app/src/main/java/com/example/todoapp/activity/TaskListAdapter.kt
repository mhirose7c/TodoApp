package com.example.todoapp.activity

import android.content.Intent
import com.example.todoapp.R
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.todoapp.model.TaskModel
import kotlinx.android.synthetic.main.task_list.view.*
import kotlin.collections.ArrayList

class TaskListAdapter (private val taskListSet: ArrayList<TaskModel>):RecyclerView.Adapter<TaskListAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val taskId : TextView = view.task_id
        val taskName : TextView = view.title
        val taskStatus: CheckBox = view.status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.task_list, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val context = view.context
            var intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("ITEM_ID", holder.taskId.text.toString())
            context.startActivity(intent)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskId.text = taskListSet[position].id
        holder.taskStatus.isChecked = taskListSet[position].taskStatus
        holder.taskName.text = taskListSet[position].taskName
    }

    override  fun getItemCount() = taskListSet.size
}