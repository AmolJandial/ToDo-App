package com.yepyuno.todolist.util

class Constants {
    companion object{
        //Database constants
        const val DATABASE_NAME = "localDatabase"
        const val DATABASE_TABLE_LIST = "listsTable"
        const val DATABASE_TABLE_TASK = "tasksTable"
        const val DATABASE_TABLE_STEP = "stepsTable"

        //Datastore constants
        const val PREFERENCE_USERNAME = "username"
        const val PREFERENCE_IS_SYNCED = "false"
        const val PREFERENCE_NAME = "userDataStore"
        const val DEFAULT_USERNAME = ""
        const val DEFAULT_IS_SYNCED = false

        //Fragment constants
        const val LOGTAG = "logging"
    }
}