package com.example.socialmediaapp_kmm.android.home

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.socialmediaapp_kmm.android.destinations.PostDetailsDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination(start = false)
@Composable
fun Home(navigator: DestinationsNavigator) {

    val viewModel: HomeViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsUiState = viewModel.postUiState,
        onPostClick = { post -> navigator.navigate(PostDetailsDestination(postId = post.id)) },
        onProfileClick = {

            Log.e("hbhjbbhb", "Profile")
        },
        onLikeClick = {
            Log.e("hbhjbbhb", "Like.....")
        },
        onCommentClick = {
            Log.e("hbhjbbhb", "Comment....")
        },
        onFollowButtonClick = { _, _ -> },
        onBoardingFinish = { }, fetchData = { viewModel.fetchData() }
    )
}