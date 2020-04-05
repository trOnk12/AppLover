package com.example.applover.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applover.core.functional.Result
import com.example.applover.core.functional.SingleLiveData
import com.example.applover.domain.usecase.LoginCredentials
import com.example.applover.domain.usecase.LoginUseCase
import com.example.applover.util.CredentialValidator
import com.example.applover.util.EmailValidation
import com.example.applover.util.PasswordValidation
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel
@Inject constructor(
    private val credentialValidator: CredentialValidator,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState: SingleLiveData<LoginState> = SingleLiveData()
    val loginState: LiveData<LoginState>
        get() = _loginState

    private val _validationErrors: MutableLiveData<HashMap<CredentialValidator.CredentialType, String>> =
        MutableLiveData()
    val validationErrors: LiveData<HashMap<CredentialValidator.CredentialType, String>>
        get() = _validationErrors

    fun login(email: String, password: String) {
        onValidationSuccess("test","test")
//        credentialValidator.validate(
//            PasswordValidation(password),
//            EmailValidation(email),
//            onSuccess = { onValidationSuccess(email, password) },
//            onError = { errors -> onValidationError(errors) }
//        )
    }

    private fun onValidationError(errors: java.util.HashMap<CredentialValidator.CredentialType, String>) {
        _validationErrors.value = errors
    }

    private fun onValidationSuccess(email: String, password: String) {
        _validationErrors.value = null
        _loginState.value = LoginState.IsLoading

        viewModelScope.launch {
            when (val loginResult =
                loginUseCase(
                    LoginCredentials(
                        email = "login@applover.pl",
                        password = "password123"
                    )
                )) {
                is Result.Success -> _loginState.value = LoginState.Success
                is Result.Error -> _loginState.value =
                    LoginState.Failure(loginResult.exception.message)
            }
        }
    }

}

sealed class LoginState {
    object IsLoading : LoginState()
    object Success : LoginState()
    data class Failure(val message: String?) : LoginState()
}