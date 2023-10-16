package com.yepyuno.todolist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yepyuno.todolist.data.local.model.notes.Category


class TaskViewModel: ViewModel() {

    var category: MutableLiveData<Category> = MutableLiveData()


}