package com.example.applover.di.component

import com.example.applover.data.repository.LoginRepository
import com.example.applover.domain.repository.ILoginRepository

interface CoreComponent {

    fun loginRepository(): LoginRepository

}