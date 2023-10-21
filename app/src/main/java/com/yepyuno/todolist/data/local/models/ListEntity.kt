package com.yepyuno.todolist.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yepyuno.todolist.util.Constants.Companion.DATABASE_TABLE_LIST

@Entity(tableName = DATABASE_TABLE_LIST)
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var title: String,
    val isDefault: Boolean,
    var icon: String,
    var theme: String
)
