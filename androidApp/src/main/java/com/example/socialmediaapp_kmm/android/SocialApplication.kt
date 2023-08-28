package com.example.socialmediaapp_kmm.android

import android.app.Application
import com.example.socialmediaapp_kmm.android.di.AppModule
import com.example.socialmediaapp_kmm.di.getShareModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModule + getShareModule())
            androidContext(this@SocialApplication)
        }
    }
}