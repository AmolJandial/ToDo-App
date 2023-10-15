package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetUserUsecase(private val repository: Repository) {

    fun execute(): Flow<User?> = repository.getUser()

}