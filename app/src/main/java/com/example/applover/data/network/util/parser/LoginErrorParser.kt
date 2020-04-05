package com.example.applover.data.network.util.parser

import retrofit2.HttpException
import javax.inject.Inject

const val UNKNOWN_ERROR_MESSAGE = "Something went wrong"
const val UNAUTHORIZED_ERR0R_MESSAGE = "Forgot something? Wrong credentials"
const val INTERNAL_ERROR_MESSAGE = "Ops, it is on us, something went wrong."

class LoginErrorParser @Inject constructor() :
    ErrorParser {

    private val errorMessage =
        mapOf(
            401 to UNAUTHORIZED_ERR0R_MESSAGE,
            500 to INTERNAL_ERROR_MESSAGE
        )

    override fun parse(throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                errorMessage[throwable.code()] ?: UNKNOWN_ERROR_MESSAGE
            }
            else -> UNKNOWN_ERROR_MESSAGE
        }
    }
}
