package com.yepyuno.todolist.presentation.stateHolders.models

import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.util.Constants.Companion.COLOR1
import com.yepyuno.todolist.util.Constants.Companion.ICON_DEFAULT_LIST

data class ListDetailUiState(
    val list: ListEntity = ListEntity(0, "Untitled List", false, ICON_DEFAULT_LIST, COLOR1),
    var userMessage: String? = null,
    var showCreateDialog: Boolean = false
)
