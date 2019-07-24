package com.example.todoapp.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class TaskModel(
    @PrimaryKey open var id : String = UUID.randomUUID().toString(),
    @Required open var taskName : String = "",
    open var detaile: String = "",
    open var taskStatus: Boolean = false,
    open var targetDate: String = "",
    open var createdAt: Date = Date(),
    open var updatedAt: Date = Date()
): RealmObject() {
    open fun inputValid():Boolean{
        return taskName.isNotEmpty()
    }
}

