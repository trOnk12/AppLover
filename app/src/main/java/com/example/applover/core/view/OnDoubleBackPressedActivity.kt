package com.example.applover.core.view

import android.os.Handler
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class OnDoubleBackPressedActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    private var doubleBackToExitPressedOnce = false

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        doubleBackToExitPressedOnce = true;
        finish()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}