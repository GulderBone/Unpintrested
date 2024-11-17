package com.gulderbone.core.data.auth

import android.content.SharedPreferences
import com.gulderbone.core.data.mapper.AuthInfoMapper
import com.gulderbone.core.data.mapper.AuthInfoSerializableMapper
import com.gulderbone.core.domain.AuthInfo
import com.gulderbone.core.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class EncryptedSessionStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val authInfoMapper: AuthInfoMapper,
    private val authInfoSerializableMapper: AuthInfoSerializableMapper,
) : SessionStorage {

    override suspend fun get(): AuthInfo? {
        return withContext(Dispatchers.IO) {
            val json = sharedPreferences.getString(KEY_AUTH_INFO, null)
            json?.let {
                authInfoMapper.from(
                    Json.decodeFromString<AuthInfoSerializable>(it)
                )
            }
        }
    }

    override suspend fun set(info: AuthInfo?) {
        withContext(Dispatchers.IO) {
            if (info == null) {
                sharedPreferences.edit().remove(KEY_AUTH_INFO).commit()
                return@withContext
            }

            val json = Json.encodeToString(
                authInfoSerializableMapper.from(info)
            )
            sharedPreferences
                .edit()
                .putString(KEY_AUTH_INFO, json)
                .commit()
        }
    }

    companion object {
        private const val KEY_AUTH_INFO = "KEY_AUTH_INFO"
    }
}