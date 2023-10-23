package com.yepyuno.todolist.data.dataSource.localDataSource

import com.yepyuno.todolist.data.local.dao.ListDao
import com.yepyuno.todolist.data.local.dao.TaskDao
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasks
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val listDao: ListDao,
    private val taskDao: TaskDao
) {

    suspend fun getLists(): List<ListEntity> =
        listDao.getLists()

    suspend fun getListsWithTasks(): List<ListWithTasks> =
        listDao.getListsWithTasks()

    fun getListWithId(listId: Int): Flow<ListEntity?> = listDao.getList(listId)


}