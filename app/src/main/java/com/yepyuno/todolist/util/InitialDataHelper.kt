package com.yepyuno.todolist.util

import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.model.notes.Category

object InitialDataHelper {

    fun initialCategoryList(): List<Category> = listOf<Category>(
        Category(0, "My Day", "", "today_icon", null),
        Category(0, "Important", "", "star_icon", null),
        Category(0, "Tasks", "", "task_icon", null))

    fun initialUser(): User = User(0, "", "", false)

}