package com.yepyuno.todolist.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentHomeBinding
import com.yepyuno.todolist.presentation.ui.MainActivity
import com.yepyuno.todolist.presentation.viewmodel.HomeViewModel


class FragmentHome : Fragment() {

    private val categoryAdapter by lazy {
        (activity as MainActivity).categoryAdapter
    }

    private val binding by lazy{
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewmodel by lazy{
        val factory = (activity as MainActivity).homeViewModelFactory
        ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        prepareRecyclerView()
        viewCategoryItems()
        setListeners()

    }

    private fun setListeners(){
        binding.bottomBar.setOnClickListener {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply{
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply{
                duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
            }
            findNavController().navigate(FragmentHomeDirections.actionFragmentHomeToFragmentCategory())
        }
    }



    private fun prepareRecyclerView() {
        binding.content.CategoryRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

    private fun viewCategoryItems() {
        viewmodel.getCategoryList()
        viewmodel.categoryList.observe(viewLifecycleOwner){
            categoryAdapter.differ.submitList(it)
        }
    }


}