package com.yepyuno.todolist.data.repository

import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.data.repository.dataSource.LocalDataSource
import com.yepyuno.todolist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val localDataSource: LocalDataSource
): Repository {

    override suspend fun insertUser(user: User) =
        localDataSource.insertUser(user)

    override fun getUser(): Flow<User?> =
        localDataSource.getUser()

    override suspend fun insertCategory(category: Category) =
        localDataSource.insertCategory(category)

    override fun getCategory(): Flow<List<Category>> =
        localDataSource.getCategory()

}