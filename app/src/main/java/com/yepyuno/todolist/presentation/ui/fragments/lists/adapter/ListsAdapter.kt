package com.yepyuno.todolist.presentation.ui.fragments.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yepyuno.todolist.data.local.models.ListWithTasksEntity
import com.yepyuno.todolist.databinding.ListRowItemBinding
import com.yepyuno.todolist.presentation.stateHolders.models.ListWithTasks
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListsAdapter @Inject constructor(): ListAdapter<ListWithTasks, ListsAdapter.ListsViewHolder>(ListsDiffUtil) {

    private var onItemClickListener: ((ListWithTasks) -> Unit)? = null

    inner class ListsViewHolder(private val binding: ListRowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(listWithTasks: ListWithTasks){
            binding.listWithTasks = listWithTasks
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(listWithTasks)
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

    fun setOnItemClickListener(listener: (ListWithTasks) -> Unit){
        onItemClickListener = listener
    }

}

object ListsDiffUtil: DiffUtil.ItemCallback<ListWithTasks>(){
    override fun areItemsTheSame(oldItem: ListWithTasks, newItem: ListWithTasks): Boolean {
        return oldItem.listEntity.id == newItem.listEntity.id
    }

    override fun areContentsTheSame(oldItem: ListWithTasks, newItem: ListWithTasks): Boolean {
        return oldItem == newItem
    }

}