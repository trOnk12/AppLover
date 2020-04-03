package com.example.applover.data.network.service

import com.example.applover.domain.usecase.LoginCredentials
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/login")
    fun login(@Body loginCredentials: LoginCredentials)

}