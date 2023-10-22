package com.yepyuno.todolist.domain

import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.local.models.ListWithTasks
import com.yepyuno.todolist.data.repository.ListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetListsUsecase @Inject constructor(
    private val listRepository: ListRepository
){

    suspend operator fun invoke(): List<ListEntity> =
        listRepository.getLists()

}