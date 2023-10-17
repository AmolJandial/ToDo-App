package com.yepyuno.todolist.data.local.model.notes

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "CategoryTable")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var title: String?,
    var theme: Int?,
    var icon: Int,
    val isDefault: Boolean,
    var tasks: List<Task>?

)
