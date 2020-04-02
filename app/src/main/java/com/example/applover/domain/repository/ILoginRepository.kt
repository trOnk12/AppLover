package com.example.applover.domain.repository

interface ILoginRepository {
    fun login(email: String, password: String)
}
