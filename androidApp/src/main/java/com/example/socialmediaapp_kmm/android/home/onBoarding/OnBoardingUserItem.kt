package com.example.socialmediaapp_kmm.android.home.onBoarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dipumba.ytsocialapp.android.common.fake_data.FollowsUser
import com.dipumba.ytsocialapp.android.common.theming.MediumSpacing
import com.dipumba.ytsocialapp.android.common.theming.SmallSpacing
import com.example.socialmediaapp_kmm.android.R
import com.example.socialmediaapp_kmm.android.common.components.CircleImage
import com.example.socialmediaapp_kmm.android.common.components.FollowerButton

@Composable
fun onBoardingUserItem(
    modifier: Modifier = Modifier,
    followsUser: FollowsUser,
    onUserClick: (FollowsUser) -> Unit,
    isFollowing: Boolean = false,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit
) {
    Card(modifier = modifier
        .height(150.dp)
        .width(130.dp)
        .clickable { onUserClick(followsUser) }
       ,
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(SmallSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(modifier = modifier.size(50.dp), imageUrl = followsUser.profileUrl) {
                onUserClick(followsUser)
            }
            Spacer(modifier = modifier.height(MediumSpacing))

            Text(
                text = followsUser.name,
                maxLines = 1,
                style = MaterialTheme.typography.subtitle2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = modifier.height(MediumSpacing))

            FollowerButton(
                modifier = modifier
                    .fillMaxWidth()
                    .height(30.dp),
                text = R.string.followe_Button,
                onClick = { onFollowButtonClick(isFollowing, followsUser) }
            )

        }
    }

}


@Preview
@Composable
fun show() {
    onBoardingUserItem(followsUser = FollowsUser(
        id = 1,
        name = "Mr Dip",
        profileUrl = "https://picsum.photos/200"
    ), onUserClick = {}, onFollowButtonClick = { _, _ -> })
}