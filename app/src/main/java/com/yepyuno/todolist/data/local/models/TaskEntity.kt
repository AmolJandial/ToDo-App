package com.yepyuno.todolist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yepyuno.todolist.util.Constants.Companion.DATABASE_TABLE_TASK

@Entity(tableName = DATABASE_TABLE_TASK)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var listCreatorId: Int,
    var title: String,
    var isComplete: Boolean,
    var isImportant: Boolean
)
