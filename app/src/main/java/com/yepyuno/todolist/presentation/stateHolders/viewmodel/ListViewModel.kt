package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.repository.DataStoreRepository
import com.yepyuno.todolist.presentation.stateHolders.models.HomeUiState
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    var isReady = false


    fun fetchData() {
        viewModelScope.launch {
            Log.d(LOGTAG, "fetchUser: ${Thread.currentThread().name} FETCHING DATA")
            try {
                val user = dataStoreRepository.getUser()
                _uiState.update { currentUiState ->
                    currentUiState.copy(username = user.username, isSynced = user.isSynced)
                }
            } catch (ioe: IOException) {
                _uiState.update { currentUiState ->
                    currentUiState.copy(userMessage = ioe.message.toString())
                }
            }
            isReady = true
        }
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }

}






