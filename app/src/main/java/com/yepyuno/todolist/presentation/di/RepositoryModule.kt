package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.data.repository.UserRepositoryImpl
import com.yepyuno.todolist.data.repository.dataSource.UserLocalDataSource
import com.yepyuno.todolist.domain.repository.UserRepository
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
    fun provideUserRepository(userLocalDataSource: UserLocalDataSource): UserRepository =
        UserRepositoryImpl(userLocalDataSource)

}