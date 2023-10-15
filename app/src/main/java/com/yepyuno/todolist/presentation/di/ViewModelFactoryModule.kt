package com.yepyuno.todolist.presentation.di

import com.yepyuno.todolist.domain.usecase.GetCategoryUsecase
import com.yepyuno.todolist.domain.usecase.GetUserUsecase
import com.yepyuno.todolist.domain.usecase.InsertCategoryUsecase
import com.yepyuno.todolist.domain.usecase.InsertUserUsecase
import com.yepyuno.todolist.presentation.viewmodel.HomeViewModel
import com.yepyuno.todolist.presentation.viewmodel.factory.HomeViewModelFactory
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
                                    getUserUsecase: GetUserUsecase,
                                    insertCategoryUsecase: InsertCategoryUsecase): MainViewModelFactory =
        MainViewModelFactory(insertUserUsecase, getUserUsecase, insertCategoryUsecase)

    @Provides
    @Singleton
    fun providesHomeViewModelFactory(insertCategoryUsecase: InsertCategoryUsecase,
                                     getCategoryUsecase: GetCategoryUsecase): HomeViewModelFactory =
        HomeViewModelFactory(insertCategoryUsecase, getCategoryUsecase)

}