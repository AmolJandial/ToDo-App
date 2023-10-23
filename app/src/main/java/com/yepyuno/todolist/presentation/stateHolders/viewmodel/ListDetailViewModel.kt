package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.domain.GetListWithIdUsecase
import com.yepyuno.todolist.presentation.stateHolders.models.ListDetailUiState
import com.yepyuno.todolist.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val getListWithIdUsecase: GetListWithIdUsecase
) : ViewModel() {


    private val _uiState = MutableStateFlow(ListDetailUiState())
    val uiState: StateFlow<ListDetailUiState> = _uiState.asStateFlow()

    fun fetchData(listId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val list = getListWithIdUsecase(listId)
                list.stateIn(
                    viewModelScope
                ).collect { list ->
                    list?.let {
                        _uiState.update { currentUiState ->
                            currentUiState.copy(list = it, showCreateDialog = false)
                        }
                    }
                    if(null == list){
                        _uiState.update { currentUiState ->
                            currentUiState.copy(showCreateDialog = true)
                        }
                    }
                }
            } catch (ioe: IOException) {
                _uiState.update { currentUiState ->
                    currentUiState.copy(userMessage = ioe.message.toString())
                }
            }
        }
    }

    fun updateList(){

    }

    fun createList(){

    }


    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }

    fun createDialogShown(){
        _uiState.update { currentUiState ->
            currentUiState.copy(showCreateDialog = false)
        }
    }


}