package com.dipumba.ytsocialapp.android.common.fake_data

data class FollowsUser(
    val id: Int,
    val name: String,
    val profileUrl: String,
    val isFollowing: Boolean = false
)

val sampleUsers = listOf(
    FollowsUser(
        id = 1,
        name = "Mr Dip",
        profileUrl = "https://picsum.photos/id/1/200/300"
    ),
    FollowsUser(
        id = 2,
        name = "John Cena",
        profileUrl = "https://picsum.photos/id/237/200/300"
    ),
    FollowsUser(
        id = 3,
        name = "Cristiano",
        profileUrl = "https://picsum.photos/200"
    ),
    FollowsUser(
        id = 4,
        name = "L. James",
        profileUrl = "https://picsum.photos/200"
    )
)