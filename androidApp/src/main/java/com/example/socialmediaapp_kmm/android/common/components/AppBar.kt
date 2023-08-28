package com.example.socialmediaapp_kmm.android.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.dipumba.ytsocialapp.android.common.theming.SmallElevation
import com.example.socialmediaapp_kmm.android.R
import com.example.socialmediaapp_kmm.android.destinations.HomeDestination
import com.example.socialmediaapp_kmm.android.destinations.LoginDestination
import com.example.socialmediaapp_kmm.android.destinations.PostDetailsDestination
import com.example.socialmediaapp_kmm.android.destinations.SignUpDestination
import com.example.socialmediaapp_kmm.android.destinations.SplashScreenDestination
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Composable
fun AppBar(modifier: Modifier = Modifier, onNavHostController: NavHostController) {

    val currentDestination = onNavHostController.currentDestinationAsState().value

    Surface(modifier = modifier, elevation = SmallElevation) {
        if (currentDestination?.route == SplashScreenDestination.route) {
            AnimatedVisibility(visible = true) {
            }
        } else {
            TopAppBar(
                title = { Text(text = stringResource(id = getAppBarTitle(currentDestination?.route))) },
                modifier = modifier,
                backgroundColor = MaterialTheme.colors.surface,
                actions = {

                    AnimatedVisibility(visible = currentDestination?.route == HomeDestination.route) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.person_circle_icon),
                                contentDescription = null
                            )
                        }
                    }

                },
                navigationIcon =
                if (shouldShowNavigationIcon(currentDestination?.route)) {
                    {
                        IconButton(onClick = { onNavHostController.navigateUp() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.round_arrow_back),
                                contentDescription = null
                            )
                        }

                    }

                } else {
                    null
                }

            )
        }
    }

}


private fun getAppBarTitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        HomeDestination.route -> R.string.home_destination_title
        PostDetailsDestination.route -> R.string.post_detail_title
        else -> R.string.no_destination
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return !(currentDestinationRoute == LoginDestination.route || currentDestinationRoute == SignUpDestination.route || currentDestinationRoute == HomeDestination.route || currentDestinationRoute == SplashScreenDestination.route)
}

@Preview
@Composable
fun show() {
    val onNavHostController: NavHostController = NavHostController(LocalContext.current)
    AppBar(onNavHostController = onNavHostController)
}