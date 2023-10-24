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
        const val FRAGMENT_RECYCLER = "recyclerViewCaller"
        const val FRAGMENT_NEW_LIST = "newListCaller"


        //List Constants
        const val ICON_MYDAY = "ic_my_day"
        const val ICON_IMPORTANT = "ic_important"
        const val ICON_TASKS = "ic_tasks"
        const val ICON_DEFAULT_LIST = "ic_list"
        const val COLOR1 = "listColorTheme1"
        const val COLOR2 = "listColorTheme2"
        const val COLOR3 = "listColorTheme3"
        const val COLOR4 = "listColorTheme4"
        const val COLOR5 = "listColorTheme5"
        const val COLOR6 = "listColorTheme6"
        const val COLOR7 = "listColorTheme7"
    }
}