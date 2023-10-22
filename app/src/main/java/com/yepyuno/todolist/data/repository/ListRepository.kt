package com.yepyuno.todolist.data.repository

import com.yepyuno.todolist.data.dataSource.localDataSource.LocalDataSource
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasks
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRepository @Inject constructor(
    private val localDataSource: LocalDataSource
){

    suspend fun getLists(): List<ListEntity>{
        return localDataSource.getLists()
    }

    suspend fun getListsWithTasks(): List<ListWithTasks>{
        return localDataSource.getListsWithTasks()
    }

}