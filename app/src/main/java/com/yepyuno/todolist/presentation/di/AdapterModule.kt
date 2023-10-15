package com.yepyuno.todolist.presentation.di

import android.app.Application
import android.content.Context
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.presentation.ui.home.adapter.CategoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    @Singleton
    fun providesCategoryAdapter(@ApplicationContext context: Context): CategoryAdapter = CategoryAdapter(context)
}