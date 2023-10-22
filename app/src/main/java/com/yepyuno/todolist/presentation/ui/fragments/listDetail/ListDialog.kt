package com.yepyuno.todolist.presentation.ui.fragments.listDetail

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ListDialog {

    companion object{
        fun makeDialog(context: Context) = MaterialAlertDialogBuilder(context)
            .setTitle("Test")
            .setMessage("This is a test")
            .setPositiveButton("Create") { dialog, id ->

            }
    }

}