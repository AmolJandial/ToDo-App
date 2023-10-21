package com.yepyuno.todolist.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.yepyuno.todolist.R
import com.yepyuno.todolist.presentation.ui.MainActivity
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import kotlinx.coroutines.launch


class ListFragment : Fragment() {

    private val listViewModel by lazy{ (activity as MainActivity).listViewModel }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerUiState()
        listViewModel.fetchData()
    }

    private fun observerUiState(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                listViewModel.uiState.collect(){uiState ->
                    uiState.userMessage?.let {
                        Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                        listViewModel.userMessageShown()
                    }

                    Log.d(LOGTAG, "thread: ${Thread.currentThread().name}, uiState: ${uiState.username} ${uiState.isSynced}")
                }
            }
        }
    }


}