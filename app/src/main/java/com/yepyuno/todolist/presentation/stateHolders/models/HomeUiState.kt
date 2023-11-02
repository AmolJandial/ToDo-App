package com.yepyuno.todolist.presentation.stateHolders.models

sealed class HomeUiState{

    data object Loading: HomeUiState()

    data class Success(
        val username: String = "",
        val isSynced: Boolean = false,
        val listsWithTasks: List<ListWithTasks>? = null
    ): HomeUiState()

    data class Error(val errorMessage: String): HomeUiState()

}


