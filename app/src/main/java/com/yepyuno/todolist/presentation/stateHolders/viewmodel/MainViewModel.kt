package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.repository.DataStoreRepository
import com.yepyuno.todolist.domain.listUsecases.GetListsUsecase
import com.yepyuno.todolist.domain.listUsecases.GetListsWithTasksUsecase
import com.yepyuno.todolist.presentation.stateHolders.models.HomeUiState
import com.yepyuno.todolist.util.Constants.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val getListsUsecase: GetListsUsecase,
    private val getListsWithTasksUsecase: GetListsWithTasksUsecase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var isReady = false
    var listId: Int = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("$TAG fetching user and listsWithTasks")
            try {
                val user = async { dataStoreRepository.getUser() }
                val listsWithTasks = async { getListsWithTasksUsecase() }
                _uiState.value = HomeUiState.Success(user.await().username, user.await().isSynced, listsWithTasks.await())

            }catch (e: Exception){
                _uiState.value = HomeUiState.Error(e.message.toString())
            }finally {
                isReady = true
            }
        }
    }



}






