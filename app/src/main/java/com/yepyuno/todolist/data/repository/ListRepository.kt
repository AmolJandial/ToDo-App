package com.yepyuno.todolist.data.repository

import com.yepyuno.todolist.data.dataSource.localDataSource.LocalDataSource
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.data.local.models.mapToListWithTasks
import com.yepyuno.todolist.presentation.stateHolders.models.ListsWithTasks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepository @Inject constructor(
    private val localDataSource: LocalDataSource
){

    suspend fun getLists(): List<ListEntity>{
        return localDataSource.getLists()
    }

    suspend fun getListsWithTasks(): List<ListsWithTasks>{
        val databaseResult = localDataSource.getListsWithTasks().map {
            it.mapToListWithTasks()
        }

        return databaseResult
    }

    fun getListWithTasks(listId: Int): Flow<ListWithTasksEntity>{
       return localDataSource.getListWithTasks(listId)
    }

}