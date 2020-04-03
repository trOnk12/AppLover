package com.example.applover.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.applover.R

class LoginFragment : Fragment(R.layout.login_fragment) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private val loginSharedViewModel: LoginSharedViewModel by navGraphViewModels(R.navigation.nav_graph)

}