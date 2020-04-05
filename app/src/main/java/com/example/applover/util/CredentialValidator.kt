package com.example.applover.util

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class CredentialValidator @Inject constructor() {

    sealed class CredentialType {
        object Password : CredentialType()
        object Email : CredentialType()
    }

    fun validate(
        vararg validation: IValidation,
        onSuccess: () -> Unit,
        onError: (HashMap<CredentialType, String>) -> Unit
    ) {
        HashMap<CredentialType, String>().also { errors ->
            validation.forEach { validator ->
                errors.putAll(validator.validate())
            }

            if (errors.isEmpty()) {
                onSuccess()
            } else {
                onError(errors)
            }
        }

    }

}

interface IValidation {
    fun validate(): HashMap<CredentialValidator.CredentialType, String>
}

class RegexValidator {
    companion object {
        fun validate(regex: String, value: String): Boolean {
            val pattern: Pattern = Pattern.compile(regex)
            val matcher: Matcher = pattern.matcher(value)

            return matcher.matches()
        }
    }
}

class PasswordValidation(private val password: String) : IValidation {

    override fun validate(): HashMap<CredentialValidator.CredentialType, String> {
        val errors = HashMap<CredentialValidator.CredentialType, String>()
        if (!RegexValidator.validate(
                regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{6,}\$",
                value = password
            )
        ) {
            errors[CredentialValidator.CredentialType.Password] =
                "Password must contain eight characters, at least one letter and at least one number."
        }

        return errors
    }

}

class EmailValidation(private val email: String) : IValidation {

    override fun validate(): HashMap<CredentialValidator.CredentialType, String> {
        val errors = HashMap<CredentialValidator.CredentialType, String>()
        if (!RegexValidator.validate(
                regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$",
                value = email
            )
        ) {
            errors[CredentialValidator.CredentialType.Email] =
                "Unfortunately, entered email is not valid."
        }

        return errors
    }

}
