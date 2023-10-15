package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetCategoryUsecase(private val repository: Repository) {
    fun execute(): Flow<List<Category>> = repository.getCategory()
}