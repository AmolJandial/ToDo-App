package com.yepyuno.todolist.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.yepyuno.todolist.R
import com.yepyuno.todolist.presentation.viewmodel.MainViewModel
import com.yepyuno.todolist.presentation.viewmodel.factory.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    companion object{
        const val TAG = "MainActivity"
    }
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val mainViewModel by lazy{
        ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocalData()
    }

    //Getting User Local Data from Room Database and suspending first draw of mainActivity
    private fun getLocalData(){
        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object: ViewTreeObserver.OnPreDrawListener{
            override fun onPreDraw(): Boolean {
                return if(mainViewModel.isReady){
                    Log.d(TAG, "onPreDraw: GOT DATA")
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }else{
                    Log.d(TAG, "onPreDraw: Getting Data")
                    return false
                }
            }
        })
        mainViewModel.getUser()
    }

}