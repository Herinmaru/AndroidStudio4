package com.example.todolist.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class Entity(
    @PrimaryKey(autoGenerate = false)
    var id: UUID,
    var name: String,
    var description: String,
    val creationTimestamp: Long
)