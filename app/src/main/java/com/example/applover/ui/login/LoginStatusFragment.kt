package com.example.applover.ui.login

import LoginSharedViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.applover.R
import com.example.applover.core.extension.observe

class LoginStatusFragment : Fragment(R.layout.login_status_fragment) {

    private val loginSharedViewModel: LoginSharedViewModel
            by navGraphViewModels(R.id.nav_graph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observe(loginSharedViewModel.loginState) { loginState ->
            when (loginState) {
                is LoginState.Success -> onLoginSuccess()
                is LoginState.Failure -> onLoginFailure(loginState.message)
            }
        }
    }

    private fun onLoginSuccess() {

    }

    private fun onLoginFailure(message: String?) {
        message?.let {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

}
