package com.example.applover.ui.login

import LoginSharedViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.applover.R
import com.example.applover.core.extension.observe
import com.example.applover.databinding.LoginFragmentBinding

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var loginFragmentBinding: LoginFragmentBinding
    private val loginSharedViewModel: LoginSharedViewModel
            by navGraphViewModels(R.navigation.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false)
        return loginFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginFragmentBinding.btLogin.setOnClickListener {
            loginSharedViewModel.login(
                email = loginFragmentBinding.tfEmailAddress.toString(),
                password = loginFragmentBinding.tfPassword.toString()
            )
        }
    }

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