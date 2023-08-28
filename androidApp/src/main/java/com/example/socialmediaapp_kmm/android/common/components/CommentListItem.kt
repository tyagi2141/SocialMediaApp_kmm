package com.example.socialmediaapp_kmm.android.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dipumba.ytsocialapp.android.common.fake_data.samplePosts
import com.dipumba.ytsocialapp.android.common.fake_data.sampleUsers
import com.dipumba.ytsocialapp.android.common.theming.DarkGray
import com.dipumba.ytsocialapp.android.common.theming.LargeSpacing
import com.dipumba.ytsocialapp.android.common.theming.LightGray
import com.example.socialmediaapp_kmm.android.R
import com.example.socialmediaapp_kmm.android.common.fake_data.Comment

@Composable
fun CommentListItem(
    modifier: Modifier = Modifier, comment: Comment, onProfileClick: (Int) -> Unit,
    onMoreIconClick: (Comment) -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
    ) {
        CircleImage(
            modifier = modifier
                .size(50.dp), imageUrl = comment.authorImageUrl
        ) {}
        Column() {
            Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = comment.authorName, color = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    }
                )
                Spacer(modifier = modifier.width(10.dp))
                Text(
                    modifier = modifier.weight(1f),
                    text = comment.date,
                    style = MaterialTheme.typography.caption,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1, color = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    }
                )
                Icon(
                    modifier = modifier.clickable { onMoreIconClick(comment) },
                    painter = painterResource(id = R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = if (MaterialTheme.colors.isLight) {
                        LightGray
                    } else {
                        DarkGray
                    }
                )
            }
            Text(
                text = comment.comment, color = if (MaterialTheme.colors.isLight) {
                    LightGray
                } else {
                    DarkGray
                }
            )
        }


    }
}


@Preview
@Composable
fun showPreview() {
    CommentListItem(
        comment = Comment(
            id = "comment1",
            date = "2023-06-24",
            comment = "Great post!\nI learned a lot from it.",
            authorName = sampleUsers[0].name,
            authorImageUrl = sampleUsers[0].profileUrl,
            authorId = sampleUsers[0].id,
            postId = samplePosts[0].id
        ), onMoreIconClick = {}, onProfileClick = {}
    )
}