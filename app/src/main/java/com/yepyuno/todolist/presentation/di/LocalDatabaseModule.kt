package com.yepyuno.todolist.presentation.di

import android.app.Application
import androidx.room.Room
import com.yepyuno.todolist.data.local.db.RoomDatabase
import com.yepyuno.todolist.data.local.db.dao.CategoryDAO
import com.yepyuno.todolist.data.local.db.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatabaseModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(app: Application) : RoomDatabase =
        Room.databaseBuilder(app, RoomDatabase::class.java, "local_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesUserDAO(roomDatabase: RoomDatabase) : UserDAO =
        roomDatabase.getUserDAO()

    @Provides
    @Singleton
    fun providesCategoryDAO(roomDatabase: RoomDatabase) : CategoryDAO =
        roomDatabase.getCategoryDAO()

}