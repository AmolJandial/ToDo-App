package com.yepyuno.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasks

@Dao
interface ListDao {

    @Insert
    suspend fun insertList(listEntity: ListEntity)

    @Insert
    suspend fun insertInitialList(lists: List<ListEntity>)

    @Transaction
    @Query("SELECT * FROM listsTable")
    suspend fun getListsWithTasks(): List<ListWithTasks>

    @Query("SELECT * FROM listsTable")
    suspend fun getLists(): List<ListEntity>

}