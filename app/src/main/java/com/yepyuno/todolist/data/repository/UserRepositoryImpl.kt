package com.yepyuno.todolist.data.repository

import com.yepyuno.todolist.data.local.model.User
import com.yepyuno.todolist.data.repository.dataSource.UserLocalDataSource
import com.yepyuno.todolist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun insertUser(user: User) =
        userLocalDataSource.insertUser(user)

    override fun getUser(): Flow<User?> =
        userLocalDataSource.getUser()


}