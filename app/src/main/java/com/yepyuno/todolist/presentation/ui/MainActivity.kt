package com.yepyuno.todolist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yepyuno.todolist.R
import com.yepyuno.todolist.presentation.stateHolders.viewmodel.ListViewModel
import com.yepyuno.todolist.util.Constants.Companion.LOGTAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val listViewModel by viewModels<ListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<View>(android.R.id.content)
        view.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if(listViewModel.isReady){
                    Log.d(LOGTAG, "onCreate: GOT DATA FROM DATASTORE")
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                }else{
                    false
                }
            }

        })

    }
}