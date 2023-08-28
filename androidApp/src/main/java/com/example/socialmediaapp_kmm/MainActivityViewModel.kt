package com.example.socialmediaapp_kmm

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.example.socialmediaapp_kmm.android.common.datastore.UserSetting
import com.example.socialmediaapp_kmm.android.common.datastore.toAuthResult
import kotlinx.coroutines.flow.map

class MainActivityViewModel(val dataStore: DataStore<UserSetting>):ViewModel() {
    val authToken = dataStore.data.map { it.toAuthResult().token }

}