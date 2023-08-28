package com.example.socialmediaapp_kmm.auth.domain.usecase

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData
import com.example.socialmediaapp_kmm.auth.domain.repository.AuthRepository
import com.example.socialmediaapp_kmm.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(email: String, password: String): Result<AuthResultData> {

        if (email.isBlank() || "@" !in email) {
            return Result.Error(message = "email is not proper")
        }

        if (password.isBlank() || password.length < 4) {
            return Result.Error(message = "password invalid or too short")
        }

        return repository.signIn(email, password)
    }
}