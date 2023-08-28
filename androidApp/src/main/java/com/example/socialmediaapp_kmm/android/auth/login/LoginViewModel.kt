package com.example.socialmediaapp_kmm.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialmediaapp_kmm.android.common.datastore.UserSetting
import com.example.socialmediaapp_kmm.android.common.datastore.toUserSetting
import com.example.socialmediaapp_kmm.auth.domain.usecase.SignInUseCase
import com.example.socialmediaapp_kmm.common.util.Result
import kotlinx.coroutines.launch

class LoginViewModel(val signInUseCase: SignInUseCase, val dataStore: DataStore<UserSetting>) :
    ViewModel() {

    var uiState by mutableStateOf(LoginState())

    fun signIn() {
        viewModelScope.launch {
            uiState = uiState.copy(isAuthentication = true)

            val authResult =
                signInUseCase.invoke(email = uiState.email, password = uiState.password)

            uiState = when (authResult) {
                is Result.Error -> {
                    uiState.copy(isAuthentication = false, authErrorMessage = authResult.message)
                }

                is Result.Success -> {
                    dataStore.updateData {
                        authResult.data!!.toUserSetting()
                    }
                    uiState.copy(isAuthentication = false, authenticationSuccess = true)
                }
            }

        }
    }

    fun updateEmail(email: String) {
        uiState = uiState.copy(email = email)
    }

    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password)
    }
}

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isAuthentication: Boolean = false,
    val authErrorMessage: String? = null,
    val authenticationSuccess: Boolean = false
)