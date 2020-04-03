package com.example.applover.ui.login

import LoginSharedViewModel
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.example.applover.R

class LoginStatusFragment : Fragment(R.layout.login_status_fragment) {

    private val loginSharedViewModel: LoginSharedViewModel by navGraphViewModels(R.id.nav_graph)


}