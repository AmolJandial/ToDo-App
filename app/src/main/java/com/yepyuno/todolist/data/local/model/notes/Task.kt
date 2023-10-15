package com.yepyuno.todolist.data.local.model.notes

data class Task(
    var title: String,
    var note: String?,
    val creationDate: String,
    var isImportant: Boolean,
    var isComplete: Boolean,
    var steps: List<Step>?
)
