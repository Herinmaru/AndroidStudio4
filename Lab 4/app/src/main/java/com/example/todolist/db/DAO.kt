package com.example.todolist.db

import androidx.room.*

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("select * from tasks")
    suspend fun getTasks(): List<Task>
}
