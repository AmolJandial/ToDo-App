package com.yepyuno.todolist.presentation.ui.home.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yepyuno.todolist.data.local.model.notes.Category
import com.yepyuno.todolist.databinding.ListItemCategoryBinding
import dagger.hilt.android.qualifiers.ApplicationContext

class CategoryAdapter(
    private val app: Context
): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    inner class CategoryViewHolder(private val binding: ListItemCategoryBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(category: Category){
            val id = app.resources.getIdentifier(category.icon, "drawable", app.packageName)
            binding.icon.setImageDrawable(AppCompatResources.getDrawable(app, id))
            binding.title.text = category.title
            binding.taskCount.text = (category.tasks?.count() ?: "").toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }
}