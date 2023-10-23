package com.yepyuno.todolist.presentation.ui.fragments.listDetail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.navGraphViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yepyuno.todolist.R
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListDialog:  DialogFragment(){
    private val listDetailViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailDialogFragment)


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Update List")
            .create()
    }

}