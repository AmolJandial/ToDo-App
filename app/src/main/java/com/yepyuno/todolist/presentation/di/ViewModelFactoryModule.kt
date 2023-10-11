package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import com.yepyuno.todolist.presentation.viewmodel.factory.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Provides
    @Singleton
    fun provideMainViewModelFactory(insertUserUsecase: InsertUserUsecase,
                                    getUserUsecase: GetUserUsecase): MainViewModelFactory =
        MainViewModelFactory(insertUserUsecase, getUserUsecase)

}