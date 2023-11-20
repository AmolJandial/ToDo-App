package com.yepyuno.todolist.presentation.stateHolders.models

import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.util.Constants.Companion.COLOR1
import com.yepyuno.todolist.util.Constants.Companion.ICON_DEFAULT_LIST

sealed class ListDetailUiState{

    data object Loading: ListDetailUiState()

    data class Success(
        val listWithTasksEntity: ListWithTasksEntity,
        var newTaskAdded: Boolean = false,
        var showCreateDialog: Boolean = false,
        var showUpdateDialog: Boolean = false,
        var bottomSheetDismissed: Boolean = false
    ): ListDetailUiState()

    data class Error(var userMessage: String): ListDetailUiState()

}

