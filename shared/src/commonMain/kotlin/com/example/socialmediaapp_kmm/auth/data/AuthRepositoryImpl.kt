package com.example.socialmediaapp_kmm.auth.data

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData
import com.example.socialmediaapp_kmm.auth.domain.repository.AuthRepository
import com.example.socialmediaapp_kmm.common.util.DispatcherProvider
import com.example.socialmediaapp_kmm.common.util.Result
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    val dispatcher: DispatcherProvider,
    val authService: AuthService
) : AuthRepository {
    override suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = SignUpRequest(name, email, password)
                val authResponse = authService.signUp(request)

                if (authResponse.data == null) {
                    Result.Error(message = authResponse.errorMessage!!)
                } else {
                    Result.Success(data = authResponse.data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = "Oops, we could not send your request, try later!")
            }
        }
    }

    override suspend fun signIn(email: String, password: String): Result<AuthResultData> {
        return withContext(dispatcher.io) {
            try {
                val request = SignInRequest(email, password)
                val authResponse = authService.signIn(request)

                if (authResponse.data == null) {
                    Result.Error(message = authResponse.errorMessage!!)
                } else {
                    Result.Success(data = authResponse.data.toAuthResultData())
                }
            } catch (e: Exception) {
                Result.Error(message = "Oops, we could not send your request, try later!")
            }
        }
    }
}