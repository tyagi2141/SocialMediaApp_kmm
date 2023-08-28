package com.example.socialmediaapp_kmm.android.auth.signUp

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.socialmediaapp_kmm.android.destinations.HomeDestination
import com.example.socialmediaapp_kmm.android.destinations.LoginDestination
import com.example.socialmediaapp_kmm.android.destinations.SignUpDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination(start = false)
@Composable
fun SignUp(navigator: DestinationsNavigator) {

    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onUsernameChange = viewModel::updateUserName,
        onEmailChange = viewModel::updateEmail,
        onPassword = viewModel::updatePassword,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination) {
                popUpTo(SignUpDestination.route) {
                    inclusive = true
                }
            }
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(SignUpDestination.route) {
                    inclusive = true
                }
            }
        },
        onSignUpClick = {
            viewModel.signUp()
            Log.e("jbvjdfbvb", "yess")
        }
    )
}
