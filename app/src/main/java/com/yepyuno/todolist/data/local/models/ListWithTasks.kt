package com.yepyuno.todolist.data.local.models

import androidx.room.Embedded
import androidx.room.Relation

data class ListWithTasks(
    @Embedded val listEntity: ListEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "listCreatorId"
    )
    val taskEntity: TaskEntity?

)
