package com.example.socialmediaapp_kmm.android.home

import android.content.res.Configuration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dipumba.ytsocialapp.android.common.fake_data.FollowsUser
import com.dipumba.ytsocialapp.android.common.fake_data.Post
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.dipumba.ytsocialapp.android.common.fake_data.sampleUsers
import com.dipumba.ytsocialapp.android.common.theming.SocialAppTheme
import com.example.socialmediaapp_kmm.android.common.components.PostListItem
import com.example.socialmediaapp_kmm.android.home.onBoarding.OnBoardIngUiState
import com.example.socialmediaapp_kmm.android.home.onBoarding.OnBoardingSection


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardIngUiState,
    postsUiState: PostUiState,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    fetchData: () -> Unit
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
        onRefresh = { fetchData() })


    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        contentAlignment = Alignment.Center
    ) {



       LazyColumn(modifier = modifier
            .fillMaxWidth()
            ) {

           if (onBoardingUiState.shouldShowOnBoarding) {
                 item(key = "onBoardingSection") {
                     OnBoardingSection(
                         users = onBoardingUiState.users,
                         onUserClick = { onProfileClick(it.id) },
                         onFollowButtonClick = onFollowButtonClick
                     ) {
                         onBoardingFinish()
                     }
                 }


           }

            items(
                items = postsUiState.posts,
                key = { postsUiState -> postsUiState.id }) { postItem ->
                PostListItem(
                    post = postItem,
                    onPostClick = onPostClick,
                    onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommentClick = onCommentClick
                )
            }

        }

        PullRefreshIndicator(
            refreshing = onBoardingUiState.isLoading && postsUiState.isLoading,
            state = pullRefreshState,
            modifier = modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeScreen(
                onBoardingUiState = OnBoardIngUiState(
                    users = sampleUsers,
                    shouldShowOnBoarding = true
                ),
                postsUiState = PostUiState(posts = samplePosts),
                onFollowButtonClick = { _, _ -> },
                onPostClick = {},
                onProfileClick = {},
                onLikeClick = {},
                onCommentClick = {},
                fetchData = {},
                onBoardingFinish = {}
            )
        }
    }
}
