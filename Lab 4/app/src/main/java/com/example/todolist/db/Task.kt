package com.example.todolist.db

import java.sql.Timestamp
import java.util.UUID

data class Task (
    val id: UUID,
    val name: String,
    val description: String,
    val creationTimestamp: Long) {
}