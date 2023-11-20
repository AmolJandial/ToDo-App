package com.yepyuno.todolist.presentation.stateHolders.models

import com.yepyuno.todolist.data.local.models.ListEntity

sealed class HomeUiState{

    data object Loading: HomeUiState()

    data class Success(
        val username: String = "",
        val isSynced: Boolean = false,
        val listsWithTasks: List<ListsWithTasks>? = null
    ): HomeUiState()

    data class Error(val errorMessage: String): HomeUiState()

}

data class ListsWithTasks(
    val listEntity: ListEntity,
    val taskCount: Int
)


