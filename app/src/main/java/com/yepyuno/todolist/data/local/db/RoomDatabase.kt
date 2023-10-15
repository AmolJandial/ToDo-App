package com.yepyuno.todolist.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yepyuno.todolist.data.local.db.dao.CategoryDAO
import com.yepyuno.todolist.data.local.db.dao.UserDAO
import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.model.notes.Category

@Database(
    entities = [User::class, Category::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TaskConverter::class)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO

    abstract fun getCategoryDAO(): CategoryDAO
}