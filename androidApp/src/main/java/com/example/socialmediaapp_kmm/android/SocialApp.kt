package com.example.socialmediaapp_kmm.android

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.socialmediaapp_kmm.android.common.components.AppBar
import com.example.socialmediaapp_kmm.android.destinations.HomeDestination
import com.example.socialmediaapp_kmm.android.destinations.LoginDestination
import com.example.socialmediaapp_kmm.android.destinations.SplashScreenDestination
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import kotlinx.coroutines.delay

@Composable
fun SocialApp(token: String?) {

    val navHostController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()

    val isSystemDark = isSystemInDarkTheme()

    val statusBarColor =
        if (isSystemDark) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(alpha = 0.95f)

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor, darkIcons = !isSystemDark)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AppBar(onNavHostController = navHostController) }) { innerpadding ->

        DestinationsNavHost(
            modifier = Modifier.padding(innerpadding),
            navGraph = NavGraphs.root,
            navController = navHostController
        )

    }


    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = token, block = {

        // if (token != null && token.isEmpty())

        if (token.isNullOrBlank()) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 1200,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)

            navHostController.navigate(SplashScreenDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                }
                popUpTo(SplashScreenDestination.route) {
                    inclusive = true
                }
            }
            navHostController.navigate(LoginDestination.route) {
                popUpTo(HomeDestination.route) {
                    inclusive = true
                }
                popUpTo(SplashScreenDestination.route) {
                    inclusive = true
                }
            }

        } else {

            navHostController.navigate(HomeDestination.route) {
                popUpTo(LoginDestination.route) {
                    inclusive = true
                }
                popUpTo(SplashScreenDestination.route) {
                    inclusive = true
                }
            }

        }
    })

    /*   LaunchedEffect(key1 = token, block = {
           if (token.isNullOrBlank()){

           }
          else if (token.isNullOrBlank()) {
               navHostController.navigate(LoginDestination.route) {
                   popUpTo(HomeDestination.route) {
                       inclusive = true
                   }
               }
           }else {
               navHostController.navigate(HomeDestination.route) {
                   popUpTo(LoginDestination.route) {
                       inclusive = true
                   }
               }
           }
       })*/


}