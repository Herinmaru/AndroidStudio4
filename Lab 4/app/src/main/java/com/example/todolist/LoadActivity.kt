package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.example.todolist.db.DataObject
import com.example.todolist.db.Database
import com.example.todolist.db.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoadActivity : AppCompatActivity() {
    private lateinit var database: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        database = Room.databaseBuilder(applicationContext, Database::class.java, "tasks").build()

        GlobalScope.launch {
            DataObject.tasks = database.dao().getTasks() as MutableList<Task>
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

}