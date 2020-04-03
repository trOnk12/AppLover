package com.example.applover.ui.login

import LoginSharedViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.applover.R
import com.example.applover.core.extension.observe

class LoginFragment : Fragment(R.layout.login_fragment) {

    private val loginSharedViewModel: LoginSharedViewModel
            by navGraphViewModels(R.navigation.nav_graph)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(loginSharedViewModel.loginState) { loginState ->
            when (loginState) {
                is LoginState.IsLoading -> navigateLoginStatus()
            }
        }
    }

    private fun navigateLoginStatus() {
        findNavController().navigate(R.id.loginStatusFragment)
    }

}