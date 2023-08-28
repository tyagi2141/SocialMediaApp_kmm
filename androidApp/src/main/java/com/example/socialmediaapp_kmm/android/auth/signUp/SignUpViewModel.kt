package com.example.socialmediaapp_kmm.android.auth.signUp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaapp_kmm.android.common.datastore.UserSetting
import com.example.socialmediaapp_kmm.android.common.datastore.toUserSetting
import com.example.socialmediaapp_kmm.auth.domain.usecase.SignUpUseCase
import com.example.socialmediaapp_kmm.common.util.Result
import kotlinx.coroutines.launch

class SignUpViewModel(val signUpUseCase: SignUpUseCase,val dataStore: DataStore<UserSetting>) : ViewModel() {

    var uiState by mutableStateOf(SignUpUiState())
        private set


    fun signUp() {

        viewModelScope.launch {

            uiState = uiState.copy(isAuthentication = true)

            val authResponseData = signUpUseCase.invoke(
                email = uiState.email,
                name = uiState.username,
                password = uiState.password
            )



            uiState = when (authResponseData) {


                is Result.Error -> {
                    uiState.copy(
                        isAuthentication = false,
                        authErrorMessage = authResponseData.message
                    )
                }


                is Result.Success -> {
dataStore.updateData { authResponseData.data!!.toUserSetting() }
                    uiState.copy(authenticationSuccess = true, isAuthentication = false)
                }
            }
        }

    }

    fun updateUserName(username: String) {
        uiState = uiState.copy(username = username)
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }

}

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isAuthentication: Boolean = false,
    val authErrorMessage: String? = null,
    val authenticationSuccess: Boolean = false
)