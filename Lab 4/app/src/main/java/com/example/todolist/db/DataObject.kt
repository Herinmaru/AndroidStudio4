package com.example.todolist.db

import java.util.UUID

object DataObject {
    var tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun getAllData(): List<Task> {
        return tasks.sortedBy { it.creationTimestamp }
    }

    fun getTask(id: UUID): Task {
        return tasks.first{ it.id == id }
    }

    fun deleteTask(id: UUID) {
        tasks.removeIf { it.id == id }
    }
}