package com.yepyuno.todolist.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import com.yepyuno.todolist.presentation.viewmodel.MainViewModel

class MainViewModelFactory(
    private val insertUserUsecase: InsertUserUsecase,
    private val getUserUsecase: GetUserUsecase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(insertUserUsecase, getUserUsecase) as T
    }
}