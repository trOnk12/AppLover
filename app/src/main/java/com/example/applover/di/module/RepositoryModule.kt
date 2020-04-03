package com.example.applover.di.module

import com.example.applover.data.repository.LoginRepository
import com.example.applover.domain.repository.ILoginRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideLoginRepository(loginRepository: LoginRepository): ILoginRepository

}