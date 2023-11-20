package com.yepyuno.todolist.presentation.ui.fragments.listDetail.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yepyuno.todolist.R
import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.databinding.TaskAddBottomSheetBinding
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewTaskBottomSheet: BottomSheetDialogFragment() {
    private val listDetailViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailNestedNavGraph)
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var _binding: TaskAddBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TaskAddBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setListeners()
    }


    private fun setListeners(){
        binding.addTaskButton.setOnClickListener {
            val task = TaskEntity(0, mainViewModel.listId, binding.taskEditText.text.toString(), false, false);
            listDetailViewModel.addNewTask(task);
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        listDetailViewModel.bottomSheetDismissed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}