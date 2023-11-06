package com.example.socialmediaapp_kmm.android.profiledetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dipumba.ytsocialapp.android.common.fake_data.Post
import com.example.socialmediaapp_kmm.android.common.components.PostListItem
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProfilePage(
    postItem: Post,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit
) {
    PostListItem(
        post = postItem,
        onPostClick = onPostClick,
        onProfileClick = onProfileClick,
        onLikeClick = onLikeClick,
        onCommentClick = onCommentClick
    )
}

@Preview
@Composable
fun showProfile() {
    ProfilePage(postItem = Post(
        id = "14",
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
        imageUrl = "https://picsum.photos/id/237/200/300",
        createdAt = "Mar 31, 2023",
        likesCount = 90,
        commentCount = 13,
        authorId = 3,
        authorName = "Cristiano",
        authorImage = "https://picsum.photos/id/237/200/300"
    ), onPostClick = {
        Post(
            id = "14",
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            imageUrl = "https://picsum.photos/id/237/200/300",
            createdAt = "Mar 31, 2023",
            likesCount = 90,
            commentCount = 13,
            authorId = 3,
            authorName = "Cristiano",
            authorImage = "https://picsum.photos/id/237/200/300"
        )
    }, onProfileClick = { 1 }, onLikeClick = { }) {

    }
}

