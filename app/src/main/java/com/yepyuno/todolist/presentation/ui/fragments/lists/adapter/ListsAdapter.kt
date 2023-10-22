package com.yepyuno.todolist.presentation.ui.fragments.lists.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yepyuno.todolist.data.local.models.ListWithTasks
import com.yepyuno.todolist.databinding.ListRowItemBinding
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListsAdapter @Inject constructor(): ListAdapter<ListWithTasks, ListsAdapter.ListsViewHolder>(ListsDiffUtil) {

    class ListsViewHolder(private val binding: ListRowItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(listWithTasks: ListWithTasks){
            binding.listWithTasks = listWithTasks
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ListsViewHolder =
                ListsViewHolder(ListRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        return ListsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        holder.bind(getItem(position))
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