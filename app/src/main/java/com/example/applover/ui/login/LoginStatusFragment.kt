package com.example.applover.ui.login

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.applover.R
import com.example.applover.core.extension.observe
import com.example.applover.core.extension.startTransition
import com.example.applover.databinding.LoginStatusFragmentBinding
import com.google.android.material.snackbar.Snackbar

class LoginStatusFragment : Fragment(R.layout.login_status_fragment) {

    private lateinit var loginStatusFragmentBinding: LoginStatusFragmentBinding
    private val loginViewModel: LoginViewModel
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
        observe(loginViewModel.loginState) { loginState ->
            when (loginState) {
                is LoginState.Success -> onLoginSuccess()
                is LoginState.Failure -> onLoginFailure(loginState.message)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun onLoginSuccess() {
        updateStatusMessage(requireContext().getString(R.string.succes_status))
    }

    private fun onLoginFailure(message: String?) {
        updateStatusMessage(requireContext().getString(R.string.error_status))
        informUser(message)
    }

    private fun informUser(message: String?) {
        message?.let {
            Snackbar.make(loginStatusFragmentBinding.loginStatusView, message, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun updateStatusMessage(message: String) {
        startTransition(loginStatusFragmentBinding.transitionContainer) {
            loginStatusFragmentBinding.tvStatus.apply {
                text = message
                visibility = View.VISIBLE
            }
            loginStatusFragmentBinding.progressView.visibility = View.GONE
        }
    }

}
