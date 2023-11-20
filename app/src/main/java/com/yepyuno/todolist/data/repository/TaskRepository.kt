package com.yepyuno.todolist.data.repository

import com.yepyuno.todolist.data.dataSource.localDataSource.LocalDataSource
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) {
    suspend fun insertTask(taskEntity: TaskEntity) = localDataSource.insertTask(taskEntity)

    suspend fun updateTask(taskEntity: TaskEntity) = localDataSource.updateTask(taskEntity)
}