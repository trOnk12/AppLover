package com.example.applover

import android.app.Application
import android.content.Context
import com.example.applover.di.component.CoreComponent
import com.example.applover.di.module.ContextModule

class AppLoverApp : Application() {

    lateinit var coreComponent: CoreComponent

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as AppLoverApp).coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDaggerCoreComponent()
    }

    private fun initDaggerCoreComponent() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }


}