package com.yepyuno.todolist.data.dataSource.localDataSource

import com.yepyuno.todolist.data.local.dao.ListDao
import com.yepyuno.todolist.data.local.dao.TaskDao
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.util.Constants
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val listDao: ListDao,
    private val taskDao: TaskDao
) {

    suspend fun getLists(): List<ListEntity> =
        listDao.getLists()

    suspend fun getListsWithTasks(): List<ListWithTasksEntity> =
        listDao.getListsWithTasks()

    suspend fun insertTask(taskEntity: TaskEntity) = taskDao.insertTask(taskEntity)


    fun getListWithTasks(listId: Int) = listDao.getListWithTasks(listId)

    suspend fun updateTask(taskEntity: TaskEntity){
        taskDao.updateTask(taskEntity)
    }

}