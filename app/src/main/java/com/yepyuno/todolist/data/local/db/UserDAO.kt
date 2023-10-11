package com.yepyuno.todolist.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yepyuno.todolist.data.local.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM userTable")
    fun getUser(): Flow<User?>

}