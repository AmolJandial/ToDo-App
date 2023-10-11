package com.yepyuno.todolist.data.repository.dataSourceImpl

import com.yepyuno.todolist.data.local.model.User
import com.yepyuno.todolist.data.local.db.UserDAO
import com.yepyuno.todolist.data.repository.dataSource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(
    private val userDAO: UserDAO
): UserLocalDataSource {
    override suspend fun insertUser(user: User) =
        userDAO.insertUser(user)

    override fun getUser(): Flow<User?> =
        userDAO.getUser()
}