package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.User
import com.yepyuno.todolist.domain.repository.UserRepository

class InsertUserUsecase(
    private val userRepository: UserRepository
) {
    suspend fun execute(user: User) = userRepository.insertUser(user)
}