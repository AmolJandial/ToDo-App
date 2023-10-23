package com.yepyuno.todolist.presentation.ui.fragments.lists

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialSharedAxis
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentListBinding
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.MainViewModel
import com.yepyuno.todolist.presentation.ui.MainActivity
import com.yepyuno.todolist.presentation.ui.fragments.lists.adapter.ListsAdapter
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    @Inject
    lateinit var listsAdapter: ListsAdapter

    private val mainViewModel by activityViewModels<MainViewModel>()


    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        observerUiState()
        setListeners()
        mainViewModel.fetchData()

    }

    private fun observerUiState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.uiState.collect(){ uiState ->
                    uiState.userMessage?.let {
                        Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                        mainViewModel.userMessageShown()
                    }

                    uiState.listsWithTasks?.let {
                        listsAdapter.submitList(it)
                    }


                    Log.d(LOGTAG, "thread: ${Thread.currentThread().name}," +
                            " uiState: ${uiState.username} ${uiState.isSynced}" +
                            " ${uiState.listsWithTasks?.toList() ?: "nullList"}")
                }
            }
        }
    }

    private fun setRecyclerView(){

        binding.listRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = listsAdapter
        }
    }

    private fun setListeners(){
        binding.appBar.setOnClickListener {

        }

        binding.bottomAppBar.setOnClickListener {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            mainViewModel.listId = -1
            navigateToListDetail()
        }

        listsAdapter.setOnItemClickListener {
            mainViewModel.listId = it.listEntity.id
            navigateToListDetail()
        }
    }

    private fun navigateToListDetail(){
        val action = ListFragmentDirections.actionListFragmentToListDetailFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}