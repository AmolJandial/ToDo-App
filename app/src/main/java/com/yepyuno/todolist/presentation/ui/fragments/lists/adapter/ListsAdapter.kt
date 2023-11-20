package com.yepyuno.todolist.presentation.ui.fragments.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yepyuno.todolist.databinding.ListRowItemBinding
import com.yepyuno.todolist.presentation.stateHolders.models.ListsWithTasks
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import javax.inject.Singleton

@FragmentScoped
class ListsAdapter @Inject constructor(): ListAdapter<ListsWithTasks, ListsAdapter.ListsViewHolder>(ListsDiffUtil) {

    private var onItemClickListener: ((ListsWithTasks) -> Unit)? = null

    inner class ListsViewHolder(private val binding: ListRowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(listsWithTasks: ListsWithTasks){
            binding.listWithTasks = listsWithTasks
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(listsWithTasks)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        val binding = ListRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(listener: (ListsWithTasks) -> Unit){
        onItemClickListener = listener
    }

}

object ListsDiffUtil: DiffUtil.ItemCallback<ListsWithTasks>(){
    override fun areItemsTheSame(oldItem: ListsWithTasks, newItem: ListsWithTasks): Boolean {
        return oldItem.listEntity.id == newItem.listEntity.id
    }

    override fun areContentsTheSame(oldItem: ListsWithTasks, newItem: ListsWithTasks): Boolean {
        return oldItem == newItem
    }

}