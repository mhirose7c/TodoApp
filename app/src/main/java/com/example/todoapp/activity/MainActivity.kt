package com.example.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.model.TaskModel
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "All Tasks"

        val itemListView = task_list
        itemListView.layoutManager = LinearLayoutManager(this)

        Realm.init(this)

        val createButton = this.findViewById<ImageButton>(R.id.createButton)
        createButton.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        val itemListView = task_list

        // 一覧表示
        val realm = Realm.getDefaultInstance()
        val resultList = ArrayList<TaskModel>()
        resultList.addAll(realm.where(TaskModel::class.java).findAll())
        itemListView.adapter = TaskListAdapter(resultList)

        super.onStart()
    }

}
