package com.yepyuno.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {

    @Insert
    suspend fun insertList(listEntity: ListEntity)

    @Insert
    suspend fun insertInitialList(lists: List<ListEntity>)

    @Transaction
    @Query("SELECT * FROM listsTable")
    suspend fun getListsWithTasks(): List<ListWithTasksEntity>

    @Transaction
    @Query("SELECT * FROM listsTable WHERE id = :listId")
    fun getListWithTasks(listId: Int): Flow<ListWithTasksEntity>

    @Query("SELECT * FROM listsTable")
    suspend fun getLists(): List<ListEntity>

    @Query("SELECT * FROM listsTable WHERE id = :listId")
    fun getList(listId: Int): Flow<ListEntity?>
}