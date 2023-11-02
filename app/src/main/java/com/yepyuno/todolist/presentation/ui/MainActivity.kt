package com.yepyuno.todolist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yepyuno.todolist.R
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.MainViewModel
import com.yepyuno.todolist.util.Constants.Companion.TAG
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<View>(android.R.id.content)
        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if(mainViewModel.isReady){
                    Timber.d("$TAG ViewModel is ready with a state")
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                }else{
                    false
                }
            }

        })

    }
}