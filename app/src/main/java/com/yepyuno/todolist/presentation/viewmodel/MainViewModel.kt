package com.yepyuno.todolist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertCategoryUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import com.yepyuno.todolist.util.InitialDataHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUserUsecase: InsertUserUsecase,
    private val getUserUsecase: GetUserUsecase,
    private val insertCategoryUsecase: InsertCategoryUsecase
): ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }

    var isReady: Boolean = false

    fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        val data = getUserUsecase.execute()
        if(data.firstOrNull() == null){
            setupData()
            Log.d(TAG, "insertUser: User Inserted")
        }
        isReady = true

    }

    private suspend fun setupData(){
        val categories = InitialDataHelper.initialCategoryList()
        insertUserUsecase.execute(InitialDataHelper.initialUser())
        insertCategoryUsecase.execute(categories[0])
        insertCategoryUsecase.execute(categories[1])
        insertCategoryUsecase.execute(categories[2])

    }


}