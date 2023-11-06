package com.example.socialmediaapp_kmm.android.auth.login

import android.inputmethodservice.Keyboard
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dipumba.ytsocialapp.android.common.theming.ExtraLargeSpacing
import com.dipumba.ytsocialapp.android.common.theming.LargeSpacing
import com.dipumba.ytsocialapp.android.common.theming.MediumSpacing
import com.example.socialmediaapp_kmm.android.R
import com.example.socialmediaapp_kmm.android.common.components.CustomTextField

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginState,
    onEmailChange: (String) -> Unit,
    onPassword: (String) -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(
                    color = if (isSystemInDarkTheme()) {
                        MaterialTheme.colors.background
                    } else {
                        MaterialTheme.colors.surface
                    }
                )
                .padding(
                    top = ExtraLargeSpacing + LargeSpacing,
                    start = LargeSpacing + MediumSpacing,
                    end = LargeSpacing + MediumSpacing,
                    bottom = LargeSpacing
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LargeSpacing),
        ) {
            CustomTextField(
                value = uiState.email, onValueChange = onEmailChange, hint = R.string.email_hint
            )
            CustomTextField(
                value = uiState.password, onValueChange = onPassword, hint = R.string.password_hint
            )

            Button(onClick = { onSignInClick() }, modifier = modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.Login_hint))
            }

            goToSignUp(modifier, onNavigateToSignUp)
        }

        if (uiState.isAuthentication) {
            CircularProgressIndicator() }

    }

    LaunchedEffect(key1 = uiState.isAuthentication, key2 = uiState.authErrorMessage, block = {

        if (uiState.authenticationSuccess) {
            onNavigateToHome()
        }

        if (uiState.authErrorMessage != null) {
            Toast.makeText(context, "${uiState.authErrorMessage}", Toast.LENGTH_SHORT).show()
        }
    })
}


@Composable()
fun goToSignUp(modifier: Modifier = Modifier, onNavigateToSignUp: () -> Unit) {

    Row(modifier) {
        Text(text = "click here go to")
        Text(
            text = "SignUp",
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(start = 10.dp)
                .clickable { onNavigateToSignUp() })
    }
}


@Preview(showSystemUi = false)
@Composable
fun show() {

   // goToLogin(onNavigateToSignUp = {})
    LoginScreen(uiState = LoginState(email = "iam@gmail.com", "1234"),
        onEmailChange = {},
        onPassword = {}, onNavigateToHome = {}, onSignInClick = {}, onNavigateToSignUp = {})
}