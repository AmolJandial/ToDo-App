package com.yepyuno.todolist.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yepyuno.todolist.data.local.model.notes.Task

class TaskConverter {
    private val gson = Gson()

    @TypeConverter
    fun taskToString(tasks: List<Task>): String = gson.toJson(tasks)

    @TypeConverter
    fun stringToTask(taskString: String): List<Task> {
        val typeObject = object : TypeToken<List<Task>>(){}.type
        return gson.fromJson(taskString, typeObject)

    }


}