package com.yepyuno.todolist.data.repository.dataSourceImpl

import com.yepyuno.todolist.data.local.db.dao.CategoryDAO
import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.db.dao.UserDAO
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.data.repository.dataSource.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val userDAO: UserDAO,
    private val categoryDAO: CategoryDAO
): LocalDataSource {
    override suspend fun insertUser(user: User) =
        userDAO.insertUser(user)

    override fun getUser(): Flow<User?> =
        userDAO.getUser()

    override suspend fun insertCategory(category: Category) = categoryDAO.insertCategory(category)

    override fun getCategory(): Flow<List<Category>> = categoryDAO.getCategory()
}