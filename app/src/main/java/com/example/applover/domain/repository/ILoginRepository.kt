package com.example.applover.domain.repository

interface ILoginRepository {
    suspend fun login(email: String, password: String)
}
