package com.example.socialmediaapp_kmm.auth.data

import com.example.socialmediaapp_kmm.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followersCount, followingCount)
}