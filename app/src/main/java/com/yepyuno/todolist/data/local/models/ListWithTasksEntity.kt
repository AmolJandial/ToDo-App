package com.yepyuno.todolist.data.local.models

import androidx.room.Embedded
import androidx.room.Relation
import com.yepyuno.todolist.presentation.stateHolders.models.ListWithTasks

data class ListWithTasksEntity(
    @Embedded val listEntity: ListEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "listCreatorId"
    )
    val taskEntity: List<TaskEntity>?

)

fun ListWithTasksEntity.mapToListWithTasks() = ListWithTasks(
    listEntity,
    taskEntity?.count() ?: 0
)

