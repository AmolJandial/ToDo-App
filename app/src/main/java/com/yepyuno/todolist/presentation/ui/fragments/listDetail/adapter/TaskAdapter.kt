package com.yepyuno.todolist.presentation.ui.fragments.listDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yepyuno.todolist.data.local.models.TaskEntity
import com.yepyuno.todolist.databinding.TaskRowItemBinding
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TaskAdapter @Inject constructor(): ListAdapter<TaskEntity, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {

    private var onCheckClickListener: ((TaskEntity) -> Unit)? = null


    inner class TaskViewHolder(private val binding: TaskRowItemBinding) : ViewHolder(binding.root){
        fun bind(taskEntity: TaskEntity){
            binding.task = taskEntity
            binding.executePendingBindings()

            binding.checkmark.setOnClickListener {
                onCheckClickListener?.let {
                    it(taskEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnCheckClickListener(listener: (TaskEntity) -> Unit){
        onCheckClickListener = listener
    }

}

object TaskDiffUtil: DiffUtil.ItemCallback<TaskEntity>(){
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }

}