package com.example.socialmediaapp_kmm.android.common.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.dipumba.ytsocialapp.android.common.fake_data.Post
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.dipumba.ytsocialapp.android.common.theming.LargeSpacing
import com.dipumba.ytsocialapp.android.common.theming.MediumSpacing
import com.dipumba.ytsocialapp.android.common.theming.SocialAppTheme
import com.example.socialmediaapp_kmm.android.R

@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    isDetailScreen: Boolean = false
) {

    Column(modifier = modifier
        .fillMaxWidth()
        .aspectRatio(ratio = 0.7f)
        .background(color = MaterialTheme.colors.surface)
        .clickable { onPostClick(post) }) {
        PostItemHeader(
            name = post.authorName,
            profileUrl = post.authorImage,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.authorId) }
        )
        Log.e("kjnjknjknk", "${post.authorName} == ${post.id} == ${post.imageUrl}")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(post.imageUrl)
                .crossfade(true)
                .memoryCachePolicy(
                    CachePolicy.DISABLED
                )
                .build(),
            contentDescription = null,
            modifier = modifier
                .fillMaxWidth()
                .padding(LargeSpacing).weight(1f),
            contentScale = ContentScale.Crop,
            placeholder = if (MaterialTheme.colors.isLight) {
                painterResource(id = R.drawable.light_image_place_holder)
            } else {
                painterResource(id = R.drawable.dark_image_place_holder)
            }
        )


        PostLikesRow(
            likesCount = post.likesCount,
            commentsCount = post.commentCount,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick
        )

        Text(
            text = post.text,
            style = MaterialTheme.typography.body2,
            modifier = modifier.padding(horizontal = LargeSpacing),
            maxLines = if (isDetailScreen) {
                20
            } else {
                2
            },
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun PostItemHeader(
    modifier: Modifier = Modifier,
    name: String,
    profileUrl: String,
    date: String,
    onProfileClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(
                horizontal = LargeSpacing,
                vertical = MediumSpacing
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)

    ) {
        CircleImage(imageUrl = profileUrl, modifier = modifier.size(50.dp)) { onProfileClick() }
        Text(text = name, style = MaterialTheme.typography.h6)
        Box(
            modifier = modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(
                    color = if (MaterialTheme.colors.isLight) {
                        Color.LightGray
                    } else {
                        Color.DarkGray
                    }
                )
        )

        Text(text = date, style = MaterialTheme.typography.caption, modifier = modifier.weight(1f))
        Icon(
            modifier = modifier,
            painter = painterResource(id = R.drawable.round_more_horiz_24),
            contentDescription = null
        )
    }
}


@Composable
fun PostLikesRow(
    modifier: Modifier = Modifier,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    likesCount: Int,
    commentsCount: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 0.dp,
                horizontal = MediumSpacing
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onLikeClick) {
            Icon(
                painter = painterResource(id = R.drawable.like_icon_outlined),
                contentDescription = null
            )
        }

        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp)
        )
        Spacer(modifier = modifier.width(MediumSpacing))

        IconButton(onClick = onCommentClick) {
            Icon(
                painter = painterResource(id = R.drawable.chat_icon_outlined),
                contentDescription = null
            )
        }

        Text(
            text = "$commentsCount",
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 18.sp)
        )


    }

}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostListItemPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostListItem(
                post = samplePosts.first(),
                onPostClick = {},
                onProfileClick = {},
                onCommentClick = {},
                onLikeClick = {}
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostLikesRowPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostLikesRow(
                likesCount = 12,
                commentsCount = 2,
                onLikeClick = {},
                onCommentClick = {}
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PostHeaderPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.surface) {
            PostItemHeader(
                name = "Mr Rahul",
                profileUrl = "",
                date = "20 min",
                onProfileClick = {}
            )
        }
    }
}


