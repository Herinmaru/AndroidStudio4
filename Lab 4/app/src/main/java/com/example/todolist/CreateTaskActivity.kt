package com.example.todolist

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.todolist.databinding.ActivityCreateTaskBinding
import com.example.todolist.db.DataObject
import com.example.todolist.db.Database
import com.example.todolist.db.Entity
import com.example.todolist.db.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, Database::class.java, "tasks").build()

        binding.createTaskButton.setOnClickListener {
            val task_name = binding.createTaskName.text.toString()
            val task_description = binding.createTaskDescription.text.toString()

            if (task_name.trim{ it <= ' ' }.isEmpty()) {
                requiredField()
            } else {
                val currentTimestamp = System.currentTimeMillis()
                val task = Task(UUID.randomUUID(), task_name, task_description, currentTimestamp)
                DataObject.addTask(task)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(task.id, task_name, task_description, currentTimestamp))
                }
                goToMainActivity()
            }
        }

        binding.backButton.setOnClickListener {
            goToMainActivity()
        }
    }

    fun requiredField() {
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setMessage(getString(R.string.required_field))
            setPositiveButton("OK", null)
            show()
        }
    }

    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}