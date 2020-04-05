package com.example.applover.data.network.api

import com.example.applover.domain.usecase.LoginCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("api/v1/login")
    suspend fun login(@Body loginCredentials: LoginCredentials)

}