package com.example.applover.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.applover.R
import com.example.applover.core.extension.observe
import com.example.applover.databinding.LoginStatusFragmentBinding

class LoginStatusFragment : Fragment(R.layout.login_status_fragment) {

    private lateinit var loginStatusFragmentBinding: LoginStatusFragmentBinding
    private val loginSharedViewModel: LoginSharedViewModel
            by navGraphViewModels(R.id.nav_graph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginStatusFragmentBinding = LoginStatusFragmentBinding.inflate(inflater, container, false)
        return loginStatusFragmentBinding.root
    }

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
        loginStatusFragmentBinding.tvSuccess.visibility = View.VISIBLE
        loginStatusFragmentBinding.progressView.visibility = View.GONE
    }

    private fun onLoginFailure(message: String?) {
        message?.let {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }

}
