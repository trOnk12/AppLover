package com.example.applover.di.component

import androidx.lifecycle.ViewModelProvider
import com.example.applover.data.repository.LoginRepository
import com.example.applover.di.module.ContextModule
import com.example.applover.di.module.NetworkModule
import com.example.applover.di.module.RepositoryModule
import com.example.applover.di.module.viewmodel.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextModule::class, NetworkModule::class, RepositoryModule::class, ViewModelModule::class])
interface CoreComponent {

    fun loginRepository(): LoginRepository

    fun viewModelFactory(): ViewModelProvider.Factory

}