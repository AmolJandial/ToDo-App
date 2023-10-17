package com.yepyuno.todolist.util

import com.yepyuno.todolist.R
import com.yepyuno.todolist.data.local.model.auth.User
import com.yepyuno.todolist.data.local.model.notes.Category

object InitialDataHelper {

    fun initialCategoryList(): List<Category> = listOf<Category>(
        Category(0, "My Day", R.color.category_color1, R.drawable.today_icon, true, null),
        Category(0, "Important", R.color.category_color1, R.drawable.star_icon, true, null),
        Category(0, "Tasks", R.color.category_color1, R.drawable.task_icon, true, null))

    fun initialUser(): User = User(0, "", "", false)

}