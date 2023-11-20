package com.yepyuno.todolist.presentation.stateHolders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.domain.listUsecases.GetListWithTasksUseCase
import com.yepyuno.todolist.domain.taskUsecases.InsertTaskUseCase
import com.yepyuno.todolist.domain.taskUsecases.UpdateTaskUseCase
import com.yepyuno.todolist.presentation.stateHolders.models.ListDetailUiState
import com.yepyuno.todolist.util.Constants.Companion.DEFAULT_LIST
import com.yepyuno.todolist.util.Constants.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListDetailViewModel @Inject constructor(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getListWithTasksUseCase: GetListWithTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ListDetailUiState>  = MutableStateFlow(ListDetailUiState.Loading)
    val uiState: StateFlow<ListDetailUiState> = _uiState.asStateFlow()


    fun fetchData(listId: Int) {
        Timber.d("$TAG Getting listWithTasks")
        when(listId){
            -1 -> {
                _uiState.update {
                    ListDetailUiState.Success(
                        ListWithTasksEntity(DEFAULT_LIST, null),
                        showCreateDialog = true,
                        showUpdateDialog = false
                    )
                }
            }
            else -> {
                getListWithTasksFromDb(listId)
            }
        }

    }

    private fun getListWithTasksFromDb(listId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val listWithTasks = getListWithTasksUseCase(listId)
                listWithTasks
                    .collect { data ->
                        _uiState.update {
                            ListDetailUiState.Success(
                                data,
                                showUpdateDialog = true,
                                showCreateDialog = false
                            )
                        }
                    }

            } catch (ioe: IOException) {
                _uiState.update {
                    ListDetailUiState.Error(ioe.message.toString())
                }
            }
        }
    }

    fun addNewTask(task: TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                insertTaskUseCase(task)
            }catch (e: Exception){
                _uiState.update {
                    ListDetailUiState.Error(e.message.toString())
                }
            }
        }

    }

    fun updateTask(task: TaskEntity){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                updateTaskUseCase(task)
            }catch (e: Exception){
                _uiState.update {
                    ListDetailUiState.Error(e.message.toString())
                }
            }
        }
    }

    fun bottomSheetDismissed(){
        _uiState.update {
            ListDetailUiState.Success((it as ListDetailUiState.Success).listWithTasksEntity, bottomSheetDismissed = true)
        }
    }

}