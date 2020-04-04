package com.example.applover.ui.login

import com.example.applover.di.component.CoreComponent
import com.example.applover.di.scope.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [CoreComponent::class])
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
    fun inject(loginStatusFragment: LoginStatusFragment)
}