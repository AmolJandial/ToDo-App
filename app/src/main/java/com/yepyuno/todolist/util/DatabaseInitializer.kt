package com.yepyuno.todolist.util

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yepyuno.todolist.data.local.dao.ListDao
import com.yepyuno.todolist.data.local.models.ListEntity
import com.yepyuno.todolist.util.Constants.Companion.COLOR1
import com.yepyuno.todolist.util.Constants.Companion.COLOR2
import com.yepyuno.todolist.util.Constants.Companion.COLOR3
import com.yepyuno.todolist.util.Constants.Companion.ICON_IMPORTANT
import com.yepyuno.todolist.util.Constants.Companion.ICON_MYDAY
import com.yepyuno.todolist.util.Constants.Companion.ICON_TASKS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class DatabaseInitializer(
    private val listProvider: Provider<ListDao>
): RoomDatabase.Callback() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            listProvider.get().insertInitialList(initialList)
        }
    }

    private val initialList: List<ListEntity> = listOf(
        ListEntity(0, "My Day", true, ICON_MYDAY, COLOR1),
        ListEntity(0, "Important", true, ICON_IMPORTANT, COLOR2),
        ListEntity(0, "Tasks", true, ICON_TASKS, COLOR3)
    )
}