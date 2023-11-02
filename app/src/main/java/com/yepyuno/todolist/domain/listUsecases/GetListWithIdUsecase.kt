package com.yepyuno.todolist.domain.listUsecases

import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.data.repository.ListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetListWithIdUsecase @Inject constructor(
    private val listRepository: ListRepository
) {



}