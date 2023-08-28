package com.example.socialmediaapp_kmm.android.post

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.dipumba.ytsocialapp.android.common.theming.LargeSpacing
import com.example.socialmediaapp_kmm.android.R
import com.example.socialmediaapp_kmm.android.common.components.CommentListItem
import com.example.socialmediaapp_kmm.android.common.components.PostListItem
import com.example.socialmediaapp_kmm.android.common.fake_data.sampleComments

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostUiState,
    commentsUiState: CommentsUiState,
    fetchData: () -> Unit
) {

    Log.e(
        "kjvbjkdfbvj one",
        "${postUiState.isLoading} ${commentsUiState.isLoading} ${postUiState.post}"
    )

    if (postUiState.isLoading && commentsUiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if(postUiState.post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colors.surface)
        ) {
            item(key = "post") {
                PostListItem(
                    post = postUiState.post,
                    onPostClick = {},
                    onProfileClick = {},
                    onLikeClick = {},
                    onCommentClick = {},
                    isDetailScreen = true
                )
            }

            item(key = "comments_header") {
                CommentsHeader(
                    onAddCommentClick = {}
                )
            }

            items(
                items = sampleComments,
                key = { comment -> comment.id }
            ) {
                Divider()
                CommentListItem(
                    comment = it,
                    onProfileClick = {},
                    onMoreIconClick = {}
                )
            }
        }
    }else{
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Column {
                Text(
                    text = stringResource(id = R.string.loading_error_message),
                    style = MaterialTheme.typography.caption
                )
                OutlinedButton(onClick = fetchData) {
                    Text(text = stringResource(id = R.string.retry_button_text))
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        fetchData()
    }
}


@Composable
fun CommentsHeader(modifier: Modifier = Modifier, onAddCommentClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Comments", style = MaterialTheme.typography.subtitle1)
        OutlinedButton(onClick = { onAddCommentClick }) {
            Text(text = "Ask Question")
        }
    }
}

@Preview
@Composable
fun previewPost() {
    PostDetailScreen(
        postUiState = PostUiState(isLoading = false, post = samplePosts.first()),
        commentsUiState = CommentsUiState(isLoading = false, sampleComments),
        fetchData = {  })
}