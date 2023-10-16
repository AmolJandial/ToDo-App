package com.yepyuno.todolist.util

import androidx.transition.Transition

abstract class CustomTransitionListener : Transition.TransitionListener, CustomListener{
    override fun onTransitionStart(transition: Transition) {

    }

    override fun onTransitionEnd(transition: Transition) {
        onTransitionComplete()
    }

    override fun onTransitionCancel(transition: Transition) {

    }

    override fun onTransitionPause(transition: Transition) {

    }

    override fun onTransitionResume(transition: Transition) {

    }


}