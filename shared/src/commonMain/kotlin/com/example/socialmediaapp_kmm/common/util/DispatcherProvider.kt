package com.example.socialmediaapp_kmm.common.util

import kotlinx.coroutines.CoroutineDispatcher

internal interface DispatcherProvider {
    val io:CoroutineDispatcher
    val main:CoroutineDispatcher
}

internal expect fun provideDispatcher():DispatcherProvider