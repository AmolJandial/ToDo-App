package com.yepyuno.todolist.presentation.stateHolders.models

data class HomeUiState(
    val username: String = "",
    val isSynced: Boolean = false,
    val userMessage: String? = null,
)
