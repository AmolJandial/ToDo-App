package com.yepyuno.todolist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.local.model.User
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainViewModel(
    private val insertUserUsecase: InsertUserUsecase,
    private val getUserUsecase: GetUserUsecase
): ViewModel() {

    companion object{
        const val TAG = "MainViewModel"
    }

    var isReady: Boolean = false

    fun getUser() = viewModelScope.launch(Dispatchers.IO) {
        val data = getUserUsecase.execute()
        if(data.firstOrNull() == null){
            insertUserUsecase.execute(
                User(
                    0,
                    "",
                    "",
                    false
                )
            )
            Log.d(TAG, "insertUser: User Inserted")
        }
        isReady = true
        Log.d(TAG, "getUser: Got User ${data.asLiveData().value?.isSynced}")
    }


}