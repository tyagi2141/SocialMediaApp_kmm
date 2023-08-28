package com.example.socialmediaapp_kmm.android.home.onBoarding

import com.dipumba.ytsocialapp.android.common.fake_data.FollowsUser

data class OnBoardIngUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = emptyList(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)
