package com.yepyuno.todolist.data.local.models

import androidx.room.Embedded
import androidx.room.Relation
import com.yepyuno.todolist.presentation.stateHolders.models.ListsWithTasks

data class ListWithTasksEntity(
    @Embedded val listEntity: ListEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "listCreatorId"
    )
    val taskEntity: List<TaskEntity>?

)

fun ListWithTasksEntity.mapToListWithTasks() = ListsWithTasks(
    listEntity,
    taskEntity?.count() ?: 0
)

