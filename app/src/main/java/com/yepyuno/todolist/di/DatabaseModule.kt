package com.yepyuno.todolist.di

import android.content.Context
import androidx.room.Room
import com.yepyuno.todolist.data.local.LocalDatabase
import com.yepyuno.todolist.data.local.dao.ListDao
import com.yepyuno.todolist.data.local.dao.TaskDao
import com.yepyuno.todolist.util.Constants.Companion.DATABASE_NAME
import com.yepyuno.todolist.util.DatabaseInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        listProvider: Provider<ListDao>
    ): LocalDatabase =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            DATABASE_NAME
        )
            .addCallback(DatabaseInitializer(listProvider))
            .build()

    @Provides
    @Singleton
    fun provideListDao(localDatabase: LocalDatabase): ListDao =
        localDatabase.getListDao()

    @Provides
    @Singleton
    fun provideTaskDao(localDatabase: LocalDatabase): TaskDao =
        localDatabase.getTaskDao()


}