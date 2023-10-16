package com.yepyuno.todolist.util

import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnCancelListener
import android.content.DialogInterface.OnClickListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yepyuno.todolist.R

object MaterialDialogBuilder {

    fun dialog(
        context: Context,
        listener: OnClickListener
    ) = MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_App_MaterialDialogTheme)
        .setTitle("New List")
        .setView(R.layout.dialog_content)
        .setNegativeButton("Cancel", listener)
        .setPositiveButton("Create List", listener)



}