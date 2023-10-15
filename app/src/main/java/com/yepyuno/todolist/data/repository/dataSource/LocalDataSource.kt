package com.yepyuno.todolist.data.repository.dataSource

import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.model.notes.Category
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertUser(user: User)
    fun getUser(): Flow<User?>

    suspend fun insertCategory(category: Category)
    fun getCategory(): Flow<List<Category>>

}