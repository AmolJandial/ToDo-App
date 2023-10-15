package com.yepyuno.todolist.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yepyuno.todolist.data.local.model.notes.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDAO {
    @Insert
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM categorytable")
    fun getCategory(): Flow<List<Category>>
}