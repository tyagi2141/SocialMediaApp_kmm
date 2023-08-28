package com.example.socialmediaapp_kmm.android.home.onBoarding

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.dipumba.ytsocialapp.android.common.fake_data.FollowsUser
import com.dipumba.ytsocialapp.android.common.fake_data.sampleUsers
import com.dipumba.ytsocialapp.android.common.theming.LargeSpacing
import com.dipumba.ytsocialapp.android.common.theming.MediumSpacing
import com.dipumba.ytsocialapp.android.common.theming.SocialAppTheme
import com.example.socialmediaapp_kmm.android.R

@Composable
fun OnBoardingSection(
    modifier: Modifier = Modifier,
    users: List<FollowsUser>,
    onUserClick: (FollowsUser) -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.onboarding_title),
            modifier = modifier
                .fillMaxWidth()
                .padding(top = MediumSpacing),
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.onboarding_description),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = LargeSpacing),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.heightIn(LargeSpacing))

        UserRow(
            users = users,
            onUserClick = onUserClick,
            onFollowerClick = onFollowButtonClick
        )

        OutlinedButton(
            onClick = onBoardingFinish,
            modifier = modifier
                .fillMaxWidth(fraction = 0.5f)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = LargeSpacing),
            shape = RoundedCornerShape(percent = 50)
        ) {
            Text(text = stringResource(id = R.string.onboarding_done_button))
        }
    }
}

@Composable
fun UserRow(
    modifier: Modifier = Modifier,
    users: List<FollowsUser>,
    onUserClick: (FollowsUser) -> Unit,
    onFollowerClick: (Boolean, FollowsUser) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(LargeSpacing),
        contentPadding = PaddingValues(horizontal = LargeSpacing), modifier = Modifier
    ) {
        items(items = users, key = { followUser -> followUser.id }) {
            onBoardingUserItem(
                followsUser = it,
                onUserClick = onUserClick,
                onFollowButtonClick = onFollowerClick
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingSectionPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            OnBoardingSection(
                users = sampleUsers,
                onUserClick = {},
                onFollowButtonClick = { _, _ -> },
                onBoardingFinish = {}
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun UsersRowPreview() {
    SocialAppTheme {
        Surface(color = MaterialTheme.colors.background) {
            UserRow(
                users = sampleUsers,
                onUserClick = {},
                onFollowerClick = { _, _ -> },
                modifier = Modifier.padding(vertical = LargeSpacing)
            )
        }
    }
}
