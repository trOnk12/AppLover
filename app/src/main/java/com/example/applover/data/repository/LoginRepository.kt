package com.example.applover.data.repository

import com.example.applover.data.network.api.LoginApi
import com.example.applover.data.network.service.LoginService
import com.example.applover.domain.repository.ILoginRepository
import com.example.applover.domain.usecase.LoginCredentials
import javax.inject.Inject

class LoginRepository
@Inject constructor(
    private val loginService: LoginService
) : ILoginRepository {

    override suspend fun login(email: String, password: String) {
        loginService(LoginCredentials(email,password))
    }

}