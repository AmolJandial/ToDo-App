package com.yepyuno.todolist.domain.usecase

import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.domain.repository.Repository

class InsertCategoryUsecase(private val repository: Repository) {
    suspend fun execute(category: Category) = repository.insertCategory(category)
}