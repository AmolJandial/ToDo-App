package com.yepyuno.todolist.presentation.ui.home.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.databinding.ListItemCategoryBinding
import dagger.hilt.android.qualifiers.ApplicationContext



class CategoryDiffCallback: DiffUtil.ItemCallback<Category>(){
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem

    }

}
class CategoryAdapter(
    private val app: Context
): ListAdapter<Category,CategoryAdapter.CategoryViewHolder>(CategoryDiffCallback()) {


    class CategoryViewHolder(val binding: ListItemCategoryBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(category: Category){
            binding.categoryData = category
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return CategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}