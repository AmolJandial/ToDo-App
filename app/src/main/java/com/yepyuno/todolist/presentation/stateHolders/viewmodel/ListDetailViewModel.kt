package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import android.content.DialogInterface
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.domain.GetListWithIdUsecase
import com.yepyuno.todolist.presentation.stateHolders.models.ListDetailUiState
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
                        showCreateListDialog()
                    }
                }
            } catch (ioe: IOException) {
                _uiState.update { currentUiState ->
                    currentUiState.copy(userMessage = ioe.message.toString())
                }
            }
        }
    }


    fun showUpdateListDialog(){

        _uiState.update { currentUiState ->
            currentUiState.copy(showUpdateDialog = true)
        }
    }

    private fun showCreateListDialog(){
        _uiState.update { currentUiState ->
            currentUiState.copy(showCreateDialog = true)
        }
    }

    fun userMessageShown() {
        _uiState.update { currentUiState ->
            currentUiState.copy(userMessage = null)
        }
    }

    fun createListDialogShown(){
        _uiState.update { currentUiState ->
            currentUiState.copy(showCreateDialog = false)
        }
    }

    fun updateListDialogShown(){
        _uiState.update { currentUiState ->
            currentUiState.copy(showUpdateDialog = false)
        }
    }


}