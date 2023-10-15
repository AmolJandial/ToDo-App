package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.data.local.db.dao.CategoryDAO
import com.yepyuno.todolist.data.local.db.dao.UserDAO
import com.yepyuno.todolist.data.repository.dataSource.LocalDataSource
import com.yepyuno.todolist.data.repository.dataSourceImpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideUserLocalDataSource(userDAO: UserDAO,
                                   categoryDAO: CategoryDAO): LocalDataSource =
        LocalDataSourceImpl(userDAO, categoryDAO)

}