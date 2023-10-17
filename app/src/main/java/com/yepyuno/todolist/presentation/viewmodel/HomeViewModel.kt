package com.yepyuno.todolist.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.domain.usecase.GetCategoryUsecase
import com.yepyuno.todolist.domain.usecase.InsertCategoryUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoryUsecase: GetCategoryUsecase
): ViewModel() {

    companion object{
        const val TAG = "HomeViewModel"
    }

    val categoryList: MutableLiveData<List<Category>> = MutableLiveData()

    fun getCategoryList() = viewModelScope.launch(Dispatchers.IO) {
        getCategoryUsecase.execute().collect(){
            Log.d(TAG, "getCategoryList: $it")
            categoryList.postValue(it)
            Log.d(TAG, "getCategoryList: got Categories ${categoryList.value}")
        }
    }
    }

