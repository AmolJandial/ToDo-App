package com.yepyuno.todolist.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yepyuno.todolist.domain.usecase.GetCategoryUsecase
import com.yepyuno.todolist.domain.usecase.InsertCategoryUsecase
import com.yepyuno.todolist.presentation.viewmodel.HomeViewModel

class HomeViewModelFactory(
    private val insertCategoryUsecase: InsertCategoryUsecase,
    private val getCategoryUsecase: GetCategoryUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(insertCategoryUsecase, getCategoryUsecase) as T
    }
}