package com.yepyuno.todolist.presentation.ui.fragments.listDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import com.google.android.material.transition.MaterialSharedAxis
import com.yepyuno.todolist.R
import com.yepyuno.todolist.databinding.FragmentListDetailBinding
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ListDetailFragment : Fragment() {

    private val listDetailViewModel by viewModels<ListDetailViewModel>()

    private var _binding: FragmentListDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        _binding = FragmentListDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            ListDialog.makeDialog(requireContext()).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}