package com.example.socialmediaapp_kmm.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.example.socialmediaapp_kmm.MainActivityViewModel
import com.example.socialmediaapp_kmm.android.auth.login.LoginViewModel
import com.example.socialmediaapp_kmm.android.auth.signUp.SignUpViewModel
import com.example.socialmediaapp_kmm.android.common.datastore.UserSettingSerialize
import com.example.socialmediaapp_kmm.android.home.HomeViewModel
import com.example.socialmediaapp_kmm.android.post.PostDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { PostDetailViewModel() }

    single {
        DataStoreFactory.create(serializer = UserSettingSerialize, produceFile = {
            androidContext().dataStoreFile(fileName = "app_user_setting")
        })
    }
}