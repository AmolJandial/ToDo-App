package com.yepyuno.todolist.bindingAdapters

import android.graphics.drawable.Icon
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.IconCompat
import androidx.databinding.BindingAdapter
import com.yepyuno.todolist.data.local.models.TaskEntity

class ListItemBinding {

    companion object{

        @BindingAdapter("iconName", "iconTheme", requireAll = true)
        @JvmStatic
        fun ImageView.setIcon(iconName: String, iconTheme: String){
            val iconNameResId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
            val iconThemeResId = context.resources.getIdentifier(iconTheme, "color", context.packageName)
            val icon = Icon.createWithResource(context, iconNameResId)
            icon.setTint(context.getColor(iconThemeResId))
            setImageIcon(icon)

        }

        @BindingAdapter("setTitle")
        @JvmStatic
        fun TextView.setTitle(title: String){
            text = title
        }

        @BindingAdapter("setListCount")
        @JvmStatic
        fun TextView.setListCount(taskCount: Int){
            text = when(taskCount){
                0 -> ""
                else -> taskCount.toString()
            }
        }



    }

}