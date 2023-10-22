package com.yepyuno.todolist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yepyuno.todolist.data.local.dao.ListDao
import com.yepyuno.todolist.data.local.dao.TaskDao
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.StepEntity
import com.yepyuno.todolist.data.local.models.TaskEntity

@Database(entities = [ListEntity::class, StepEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = true)
abstract class LocalDatabase: RoomDatabase() {

    abstract fun getListDao(): ListDao
    abstract fun getTaskDao(): TaskDao

}