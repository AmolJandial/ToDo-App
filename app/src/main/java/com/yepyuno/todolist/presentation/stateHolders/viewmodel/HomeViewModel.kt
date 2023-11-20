package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.repository.DataStoreRepository
import com.yepyuno.todolist.domain.listUsecases.GetListsWithTasksUsecase
import com.yepyuno.todolist.presentation.stateHolders.models.HomeUiState
import com.yepyuno.todolist.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val getListsWithTasksUsecase: GetListsWithTasksUsecase
) : ViewModel() {


    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()



    init {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("${Constants.TAG} fetching user and listsWithTasks")
            try {
                val user = async { dataStoreRepository.getUser() }
                val listsWithTasks = async { getListsWithTasksUsecase() }
                _uiState.value = HomeUiState.Success(user.await().username, user.await().isSynced, listsWithTasks.await())

            }catch (e: Exception){
                _uiState.value = HomeUiState.Error(e.message.toString())
            }
        }
    }

}