package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.User
import com.yepyuno.todolist.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUsecase(private val userRepository: UserRepository) {

    fun execute(): Flow<User?> = userRepository.getUser()

}