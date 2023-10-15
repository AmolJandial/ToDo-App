package com.yepyuno.todolist.data.local.model.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryTable")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var title: String?,
    var theme: String?,
    var icon: String,
    var tasks: List<Task>?

)
