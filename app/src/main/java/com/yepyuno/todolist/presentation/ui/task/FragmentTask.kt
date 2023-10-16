package com.yepyuno.todolist.presentation.ui.task

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialSharedAxis
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentCategoryBinding
import com.yepyuno.todolist.util.MaterialDialogBuilder


class FragmentTask : Fragment() {

    private val binding by lazy {
        FragmentCategoryBinding.inflate(layoutInflater)
    }

    private lateinit var dialog: MaterialAlertDialogBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog = MaterialDialogBuilder.dialog(requireContext(), dialogListener())
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun dialogListener() = DialogInterface.OnClickListener { dialog, which ->
        when(which){
            DialogInterface.BUTTON_POSITIVE ->{

            }
            DialogInterface.BUTTON_NEGATIVE ->{

            }
        }
    }




}