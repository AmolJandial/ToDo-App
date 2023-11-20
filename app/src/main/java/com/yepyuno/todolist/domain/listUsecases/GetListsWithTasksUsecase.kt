package com.yepyuno.todolist.domain.listUsecases

import com.yepyuno.todolist.data.repository.ListRepository
import com.yepyuno.todolist.presentation.stateHolders.models.ListsWithTasks
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetListsWithTasksUsecase @Inject constructor(
    private val listRepository: ListRepository
) {

    suspend operator fun invoke(): List<ListsWithTasks> =
        listRepository.getListsWithTasks()

}