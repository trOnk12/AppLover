package com.example.applover.data.network.util

import com.example.applover.data.network.util.parser.ErrorParser

abstract class CallAdapter<T>(
    private val errorParser: ErrorParser
) {

    suspend operator fun invoke(params: T) {
        try {
            execute(params)
        } catch (throwable: Throwable) {
            errorParser.parse(throwable).let { errorMessage ->
                throw Exception(errorMessage)
            }
        }
    }

    abstract suspend fun execute(params:T)
}