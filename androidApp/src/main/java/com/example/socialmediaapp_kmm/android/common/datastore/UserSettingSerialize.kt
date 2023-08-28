package com.example.socialmediaapp_kmm.android.common.datastore

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSettingSerialize : Serializer<UserSetting> {
    override val defaultValue: UserSetting
        get() = UserSetting()

    override suspend fun readFrom(input: InputStream): UserSetting {
        return try {
            Json.decodeFromString(
                deserializer = UserSetting.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserSetting, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserSetting.serializer(), value = t
            ).toByteArray()
        )
    }
}