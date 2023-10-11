package com.yepyuno.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yepyuno.todolist.data.local.model.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
}