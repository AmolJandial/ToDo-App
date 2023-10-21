package com.yepyuno.todolist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yepyuno.todolist.util.Constants.Companion.DATABASE_TABLE_STEP

@Entity(tableName = DATABASE_TABLE_STEP)
data class StepEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var title: String,
    var isComplete: Boolean
)
