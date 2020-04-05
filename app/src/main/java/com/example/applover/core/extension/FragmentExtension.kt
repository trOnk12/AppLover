package com.example.applover.core.extension

import android.transition.TransitionManager
import android.view.ViewGroup
import androidx.fragment.app.Fragment

fun Fragment.startTransition(viewGroup: ViewGroup, transition: () -> Unit) {
    TransitionManager.beginDelayedTransition(viewGroup)
    transition()
}
