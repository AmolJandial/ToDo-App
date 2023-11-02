package com.yepyuno.todolist.presentation.stateHolders.models

import com.yepyuno.todolist.data.local.models.ListEntity

data class ListWithTasks(
    val listEntity: ListEntity,
    val taskCount: Int
)