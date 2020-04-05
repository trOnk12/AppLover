package com.example.applover.data.network.service

import com.example.applover.data.network.api.LoginApi
import com.example.applover.data.network.util.CallAdapter
import com.example.applover.data.network.util.parser.LoginErrorParser
import com.example.applover.domain.usecase.LoginCredentials
import javax.inject.Inject

class LoginService
@Inject constructor(
    loginErrorParser: LoginErrorParser,
    private val loginApi: LoginApi
) : CallAdapter<LoginCredentials>(loginErrorParser) {

    override suspend fun execute(params: LoginCredentials) {
        loginApi.login(params)
    }

}