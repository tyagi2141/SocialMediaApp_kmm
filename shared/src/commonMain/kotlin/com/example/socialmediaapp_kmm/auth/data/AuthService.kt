package com.example.socialmediaapp_kmm.auth.data

import com.example.socialmediaapp_kmm.common.data.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthService : KtorApi() {

    suspend fun signUp(request: SignUpRequest): AuthResponse = client.post {
        endPoint("signup")
        setBody(request)
    }.body()

    suspend fun signIn(request: SignInRequest): AuthResponse = client.post {
        endPoint("signIn")
        setBody(request)
    }.body()


}