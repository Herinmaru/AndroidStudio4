package com.example.todolist.db

import androidx.room.TypeConverter
import com.google.gson.Gson


class MyTypeConverters {

    @TypeConverter
    fun tasksToString(tasks: List<Task>): String = Gson().toJson(tasks)

    @TypeConverter
    fun stringToTasks(string: String): List<Task> {
        val tasks = Gson().fromJson(string, Array<Task>::class.java)
        return tasks.toList()
    }
}