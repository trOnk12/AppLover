package com.example.applover.core.view

import android.os.Handler
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

const val BUTTON_CLICK_COUNT_TO_EXIT_APP = 2
const val DELAY_TO_WAIT_FOR_BACK_CLICK_RESPONSE = 500L

abstract class OnDoubleBackPressedActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    private var backButtonClickCount = 0

    override fun onBackPressed() {
        backButtonClickCount++

        Handler().postDelayed({
            if (backButtonClickCount == BUTTON_CLICK_COUNT_TO_EXIT_APP) {
                finish()
            } else {
                super.onBackPressed()
                backButtonClickCount = 0
            }
        }, DELAY_TO_WAIT_FOR_BACK_CLICK_RESPONSE)

    }

}