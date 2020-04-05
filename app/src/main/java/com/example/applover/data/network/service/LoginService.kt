package com.example.applover.data.network.service

import com.example.applover.domain.usecase.LoginCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("api/v1/login")
    suspend fun login(@Body loginCredentials: LoginCredentials)

}