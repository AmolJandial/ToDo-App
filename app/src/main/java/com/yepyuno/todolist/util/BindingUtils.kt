package com.yepyuno.todolist.util

import android.graphics.PorterDuff
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.yepyuno.todolist.data.local.model.notes.Category


    @BindingAdapter("app:setCategoryTitle")
    fun TextView.setCategoryTitle(category: Category?){
        text = (category?.title ?: "").toString()
    }

    @BindingAdapter("app:setTasksCount")
    fun TextView.setTasksCount(category: Category?){
        text = (category?.tasks?.count() ?: "").toString()
    }

    @BindingAdapter("app:setCategoryIcon")
    fun ImageView.setCategoryIcon(category: Category){
        setImageResource(category.icon)
        setColorFilter(ContextCompat.getColor(context, category.theme!!))
    }

