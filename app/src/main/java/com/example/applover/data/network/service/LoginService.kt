package com.example.applover.data.network.service

import com.example.applover.data.network.util.ErrorParser
import com.example.applover.data.network.api.LoginApi
import com.example.applover.data.network.util.CallAdapter
import com.example.applover.domain.usecase.LoginCredentials
import javax.inject.Inject

class LoginService
@Inject constructor(
    errorParser: ErrorParser,
    private val loginApi: LoginApi
) : CallAdapter<LoginCredentials>(errorParser) {

    override suspend fun execute(params: LoginCredentials) {
        loginApi.login(params)
    }

}