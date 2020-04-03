package com.example.applover.core.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(liveData: LiveData<T>, onDataChanged: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        it?.let { t -> onDataChanged(t) }
    })
}
