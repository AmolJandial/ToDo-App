package com.yepyuno.todolist.presentation.ui.fragments.listDetail


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navGraphViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.ListDialogViewBinding
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateListDialog : DialogFragment() {
    private val listDetailViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailDialogFragment)
    private var _binding: ListDialogViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialog: AlertDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = ListDialogViewBinding.inflate(layoutInflater)



        dialog = MaterialAlertDialogBuilder(requireActivity())
            .setTitle("Update List")
            .setView(binding.root)
            .setPositiveButton("Rename") { dialog, _ ->
                Log.d(LOGTAG, "onCreateDialog: CLICKED")
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.setOnShowListener {
            setObserver()
            textListener()
        }

        return dialog
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listDetailViewModel.uiState.collect { uiState ->
                    binding.editText.setText(uiState.list.title)
                }
            }
        }
    }

    private fun textListener() {

        binding.editText.addTextChangedListener {
            when (it.toString()) {
                "" -> {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).apply {
                        isEnabled = false
                        alpha = 0.5f
                    }
                }

                else -> {
                    dialog.getButton(DialogInterface.BUTTON_POSITIVE).apply {
                        isEnabled = true
                        alpha = 1.0f
                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}