package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.data.local.db.UserDAO
import com.yepyuno.todolist.data.repository.dataSource.UserLocalDataSource
import com.yepyuno.todolist.data.repository.dataSourceImpl.UserLocalDataSourceImpl
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
    fun provideUserLocalDataSource(userDAO: UserDAO): UserLocalDataSource =
        UserLocalDataSourceImpl(userDAO)

}