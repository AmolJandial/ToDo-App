package com.yepyuno.todolist.bindingAdapters

import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.yepyuno.todolist.R
import com.yepyuno.todolist.util.Constants
import timber.log.Timber

class TaskItemBinding {

    companion object{
        @JvmStatic
        @BindingAdapter("setStatus")
        fun ImageView.setStatus(status: Boolean){
            Timber.d("${Constants.TAG} changign checkmark")
            when(status){
                true -> setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.checked_circle))
                false -> setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.unchecked_circle))
            }
        }
    }

}