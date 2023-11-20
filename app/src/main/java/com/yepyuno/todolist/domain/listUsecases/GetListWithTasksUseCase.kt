package com.yepyuno.todolist.domain.listUsecases

import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.data.repository.ListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetListWithTasksUseCase @Inject constructor(
    private val listRepository: ListRepository
) {

    operator fun invoke(listId: Int): Flow<ListWithTasksEntity> = listRepository.getListWithTasks(listId)

}