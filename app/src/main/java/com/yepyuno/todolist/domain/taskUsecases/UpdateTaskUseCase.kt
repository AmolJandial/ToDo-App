package com.yepyuno.todolist.domain.taskUsecases

import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.data.repository.TaskRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
){

    suspend operator fun invoke(taskEntity: TaskEntity) = taskRepository.updateTask(taskEntity)

}