package com.yepyuno.todolist.presentation.stateHolders.models

import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasks

data class HomeUiState(
    val username: String = "",
    val isSynced: Boolean = false,
    val userMessage: String? = null,
    val listsWithTasks: List<ListWithTasks>? = null
)
