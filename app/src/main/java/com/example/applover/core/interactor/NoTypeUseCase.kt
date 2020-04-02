package com.example.applover.core.interactor

import com.example.applover.core.functional.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class NoTypeUseCase<in Params> {

    abstract suspend fun run(params: Params)

    suspend operator fun invoke(params: Params): Result<None> {
        return try {
            withContext(Dispatchers.IO) {
                run(params).let {
                    Result.Success(None())
                }
            }
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
    }
}

class None