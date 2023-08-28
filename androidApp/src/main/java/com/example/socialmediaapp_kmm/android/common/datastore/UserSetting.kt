package com.example.socialmediaapp_kmm.android.common.datastore

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserSetting(
    val id: Int = -1,
    val name: String= "",
    val bio: String = "",
    val avatar: String? = null,
    val token: String= "",
    val followersCount: Int = 0,
    val followingCount: Int = 0
)

fun UserSetting.toAuthResult(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followersCount, followingCount)
}

fun AuthResultData.toUserSetting(): UserSetting {
    return UserSetting(id, name, bio, avatar, token, followersCount, followingCount)
}