package com.example.socialmediaapp_kmm.auth.domain.usecase

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData
import com.example.socialmediaapp_kmm.auth.domain.repository.AuthRepository
import com.example.socialmediaapp_kmm.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase : KoinComponent {

    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): Result<AuthResultData> {
        if (name.isBlank() || name.length < 3) {
            return Result.Error(message = "enter username")
        }

        if (email.isBlank() || "@" !in email) {
            return Result.Error(message = "email is not proper")
        }

        if (password.isBlank() || password.length < 4){
            return Result.Error(message = "password invalid or too short")
        }

        return repository.signUp(name, email, password)
    }
}