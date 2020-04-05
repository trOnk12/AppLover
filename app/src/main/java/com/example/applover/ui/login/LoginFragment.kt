package com.example.applover.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.applover.AppLoverApp
import com.example.applover.R
import com.example.applover.core.extension.observe
import com.example.applover.databinding.LoginFragmentBinding
import com.example.applover.util.CredentialValidator
import java.util.*
import javax.inject.Inject

class LoginFragment : Fragment(R.layout.login_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginFragmentBinding: LoginFragmentBinding
    private val loginViewModel: LoginViewModel by navGraphViewModels(R.id.nav_graph) {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        initializeDaggerDependency()
        super.onAttach(context)
    }

    private fun initializeDaggerDependency() {
        DaggerLoginComponent
            .builder()
            .coreComponent(AppLoverApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

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
        loginFragmentBinding.apply {
            btLogin.setOnClickListener {
                loginViewModel.login(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString()
                )
            }
            etEmail.doAfterTextChanged {
                loginFragmentBinding.tfEmailAddress.apply {
                    if (isErrorEnabled) {
                        error = null
                    }
                }
            }
            etPassword.doAfterTextChanged {
                loginFragmentBinding.tfPassword.apply {
                    if (isErrorEnabled) {
                        error = null
                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(loginViewModel.validationErrors, ::handleErrors)
        observe(loginViewModel.loginState) { loginState ->
            when (loginState) {
                is LoginState.IsLoading -> navigateLoginStatus()
            }
        }
    }

    private fun handleErrors(errors: HashMap<CredentialValidator.CredentialType, String>) {
        errors.forEach { error ->
            when (error.key) {
                is CredentialValidator.CredentialType.Password -> showPasswordError(error.value)
                is CredentialValidator.CredentialType.Email -> showEmailError(error.value)
            }
        }
    }

    private fun showEmailError(value: String) {
        loginFragmentBinding.tfEmailAddress.error = value
    }

    private fun showPasswordError(value: String) {
        loginFragmentBinding.tfPassword.error = value
    }

    private fun navigateLoginStatus() {
        val extras = FragmentNavigatorExtras(
            loginFragmentBinding.ivLogo to "iv_logo"
        )
        findNavController().navigate(R.id.loginStatusFragment, null, null, extras)
    }

}