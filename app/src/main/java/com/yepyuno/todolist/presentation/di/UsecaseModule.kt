package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.domain.repository.Repository
import com.yepyuno.todolist.domain.usecase.GetCategoryUsecase
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertCategoryUsecase
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
    fun provideInsertUserUsecase(repository: Repository): InsertUserUsecase =
        InsertUserUsecase(repository)

    @Provides
    @Singleton
    fun provideGetUserUsecase(repository: Repository): GetUserUsecase =
        GetUserUsecase(repository)

    @Provides
    @Singleton
    fun provideGetCategoryUsecase(repository: Repository): GetCategoryUsecase =
        GetCategoryUsecase(repository)

    @Provides
    @Singleton
    fun provideInsertCategoryUsecase(repository: Repository): InsertCategoryUsecase =
        InsertCategoryUsecase(repository)


}