package com.example.socialmediaapp_kmm.di

import com.example.socialmediaapp_kmm.auth.data.AuthRepositoryImpl
import com.example.socialmediaapp_kmm.auth.data.AuthService
import com.example.socialmediaapp_kmm.auth.domain.repository.AuthRepository
import com.example.socialmediaapp_kmm.auth.domain.usecase.SignInUseCase
import com.example.socialmediaapp_kmm.auth.domain.usecase.SignUpUseCase
import com.example.socialmediaapp_kmm.common.util.provideDispatcher
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignInUseCase() }
    factory { SignUpUseCase() }

}

private val utilityModule = module {
    factory { provideDispatcher() }
}

fun getShareModule() = listOf(authModule, utilityModule)

