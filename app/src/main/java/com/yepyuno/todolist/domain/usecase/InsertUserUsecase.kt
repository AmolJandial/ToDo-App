package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.domain.repository.Repository

class InsertUserUsecase(
    private val repository: Repository
) {
    suspend fun execute(user: User) = repository.insertUser(user)
}