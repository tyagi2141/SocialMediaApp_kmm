package com.example.socialmediaapp_kmm.android.auth.login

import androidx.compose.runtime.Composable
import com.example.socialmediaapp_kmm.android.destinations.HomeDestination
import com.example.socialmediaapp_kmm.android.destinations.LoginDestination
import com.example.socialmediaapp_kmm.android.destinations.SignUpDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(navigator: DestinationsNavigator) {
    val viewModel: LoginViewModel = koinViewModel()

    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChange = viewModel::updateEmail,
        onPassword = viewModel::updatePassword,
        onNavigateToSignUp = {
            navigator.navigate(SignUpDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        },
        onSignInClick = { viewModel.signIn() },
        onNavigateToHome = {
            navigator.navigate(HomeDestination) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
            }
        }
    )
}