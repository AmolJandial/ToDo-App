package com.yepyuno.todolist.data.repository.dataSource

import com.yepyuno.todolist.data.local.model.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun insertUser(user: User)
    fun getUser(): Flow<User?>

}