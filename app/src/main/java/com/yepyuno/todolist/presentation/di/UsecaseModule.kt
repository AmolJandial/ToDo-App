package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.domain.repository.UserRepository
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UsecaseModule {

    @Provides
    @Singleton
    fun provideInsertUserUsecase(userRepository: UserRepository): InsertUserUsecase =
        InsertUserUsecase(userRepository)

    @Provides
    @Singleton
    fun provideGetUserUsecase(userRepository: UserRepository): GetUserUsecase =
        GetUserUsecase(userRepository)


}