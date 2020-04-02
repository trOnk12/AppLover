package com.example.applover.domain.usecase

import com.example.applover.core.interactor.NoTypeUseCase
import com.example.applover.domain.repository.ILoginRepository
import javax.inject.Inject

class LoginUseCase
@Inject constructor(
    private val loginRepository: ILoginRepository
) : NoTypeUseCase<LoginCredentials>() {

    override suspend fun run(params: LoginCredentials) {
        loginRepository.login(params.email, params.password)
    }

}

data class LoginCredentials(
    val email: String,
    val password: String
)