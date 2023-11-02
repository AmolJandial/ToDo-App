package com.yepyuno.todolist.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yepyuno.todolist.data.local.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasksTable WHERE listCreatorId = :taskId")
    fun getTasksByListId(taskId: Int): Flow<List<TaskEntity>?>
}