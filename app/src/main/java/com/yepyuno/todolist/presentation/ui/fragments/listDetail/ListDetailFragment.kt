package com.yepyuno.todolist.presentation.ui.fragments.listDetail

import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentListDetailBinding
import com.yepyuno.todolist.presentation.stateHolders.models.ListDetailUiState
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.MainViewModel
import com.yepyuno.todolist.presentation.ui.fragments.listDetail.adapter.TaskAdapter
import com.yepyuno.todolist.util.Constants.Companion.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ListDetailFragment : Fragment() {

    @Inject
    lateinit var taskAdapter: TaskAdapter

    private val listDetailViewModel by hiltNavGraphViewModels<ListDetailViewModel>(R.id.listDetailNestedNavGraph)
    private val mainViewModel by activityViewModels<MainViewModel>()

    private var _binding: FragmentListDetailBinding? = null
    private val binding get() = _binding!!


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
        setRecyclerView()
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
                taskAdapter.submitList(uiState.listWithTasksEntity.taskEntity)
                if(uiState.bottomSheetDismissed) binding.fab.show()
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

        taskAdapter.setOnCheckClickListener {
            it.isComplete = !it.isComplete
            listDetailViewModel.updateTask(it)
        }

    }

    private fun setRecyclerView() {
        binding.taskRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}