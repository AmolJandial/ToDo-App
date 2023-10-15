package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.data.repository.RepositoryImpl
import com.yepyuno.todolist.data.repository.dataSource.LocalDataSource
import com.yepyuno.todolist.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(localDataSource: LocalDataSource): Repository =
        RepositoryImpl(localDataSource)

}