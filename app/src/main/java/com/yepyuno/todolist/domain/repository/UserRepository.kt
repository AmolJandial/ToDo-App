package com.yepyuno.todolist.domain.repository

import com.yepyuno.todolist.data.local.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: User)
    fun getUser(): Flow<User?>
}