package com.example.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities=[Entity::class], version=1)
@TypeConverters(MyTypeConverters::class)
abstract class Database : RoomDatabase() {
    abstract fun dao(): DAO
}