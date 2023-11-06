package com.example.socialmediaapp_kmm.android.post

import android.util.Log
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


@Destination
@Composable
fun PostDetails(navigator: DestinationsNavigator?, postId: String) {

    // val viewModel: PostDetailViewModel = koinViewModel()


    val viewModel: PostDetailViewModel = koinViewModel()

    PostDetailScreen(
        postUiState = viewModel.postUiState,
        commentsUiState = viewModel.commentsUiState,
        fetchData = { viewModel.fetchData(postId) }
    )
}



