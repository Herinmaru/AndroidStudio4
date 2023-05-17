package com.example.todolist
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.todolist.databinding.ActivityCreateTaskBinding
import com.example.todolist.databinding.ActivityMoreInfoBinding
import com.example.todolist.db.DataObject
import com.example.todolist.db.Database
import com.example.todolist.db.Entity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID
import android.content.DialogInterface




class MoreInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoreInfoBinding
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoreInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, Database::class.java, "tasks").build()

        val id = UUID.fromString(intent.getStringExtra("id"))

        val name = DataObject.getTask(id).name
        val description = DataObject.getTask(id).description
        val creationTimestamp = DataObject.getTask(id).creationTimestamp

        binding.showTaskName.setText(name)
        binding.showTaskDescription.setText(description)

        binding.doneButton.setOnClickListener {
            DataObject.deleteTask(id)
            GlobalScope.launch {
                database.dao().deleteTask(Entity(id, name, description, creationTimestamp))
            }
            congratulations()
        }

        binding.backButton.setOnClickListener {
            goToMainActivity()
        }

    }

    fun congratulations() {
        val builder = AlertDialog.Builder(this)

        with(builder)
        {
            setMessage(getString(R.string.congratulations))
            setPositiveButton("OK") { dialog, Button -> goToMainActivity() }
            show()
        }
    }

    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}