package com.yepyuno.todolist.presentation.ui.fragments.listDetail.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.TaskAddBottomSheetBinding
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel

class NewTaskBottomSheet: BottomSheetDialogFragment() {

    private var _binding: TaskAddBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val listViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailNestedNavGraph)

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
        binding.viewmodel = listViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setObservers()
        setListeners()
    }

    private fun setObservers(){

    }

    private fun setListeners(){
        binding.addTaskButton.setOnClickListener {
            listViewModel.saveTaskEnterByUser(binding.taskEditText.text.toString())
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}