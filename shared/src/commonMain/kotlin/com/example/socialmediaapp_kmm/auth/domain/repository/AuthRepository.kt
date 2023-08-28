package com.example.socialmediaapp_kmm.auth.domain.repository

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData
import com.example.socialmediaapp_kmm.common.util.Result

internal interface AuthRepository {

    suspend fun signUp(name: String, email: String, password: String): Result<AuthResultData>

    suspend fun signIn(email: String, password: String): Result<AuthResultData>
}