package com.yepyuno.todolist.presentation.ui.fragments.listDetail

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialSharedAxis
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentListDetailBinding
import com.yepyuno.todolist.presentation.stateHolders.models.ListDetailUiState
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.MainViewModel
import com.yepyuno.todolist.presentation.ui.MainActivity
import com.yepyuno.todolist.util.Constants.Companion.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class ListDetailFragment : Fragment() {

    private val listDetailViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailNestedNavGraph)
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentListDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        listDetailViewModel.fetchData(mainViewModel.listId)

        observeUiState()
        setListeners()

    }

    private fun observeUiState() {
        lifecycleScope.launch {
           viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                listDetailViewModel.uiState.collect { uiState ->
                    render(uiState)
                }
            }
        }
    }

    private fun render(uiState: ListDetailUiState) {
        when(uiState){
            is ListDetailUiState.Loading -> {
                Timber.d("$TAG LOADING...")
            }
            is ListDetailUiState.Success -> {
                binding.uiState = uiState
                Timber.d("$TAG GOT DATA = ${uiState.listWithTasksEntity}")
            }
            is ListDetailUiState.Error -> {
                Timber.d("$TAG ERROR...")
            }
        }
    }

    private fun setListeners() {

        binding.fab.setOnClickListener {
            binding.fab.hide()
            findNavController().navigate(ListDetailFragmentDirections.actionListDetailFragmentToNewTaskBottomSheet())
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

    }

    private fun createUpdateDialogListener() = DialogInterface.OnClickListener { _, which ->

    }

    private fun createNewDialogListener() = DialogInterface.OnClickListener { _, which ->

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}