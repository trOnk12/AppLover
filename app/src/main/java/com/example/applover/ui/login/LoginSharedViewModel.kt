import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

``package com.example.applover.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applover.core.functional.Result
import com.example.applover.domain.usecase.LoginCredentials
import com.example.applover.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginSharedViewModel
@Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState: MutableLiveData<LoginState> = MutableLiveData()
    val loginState: LiveData<LoginState>
        get() = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginState.IsLoading
        viewModelScope.launch {
            when (val loginResult =
                loginUseCase(LoginCredentials(email = email, password = password))) {
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