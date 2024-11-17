package com.gulderbone.core.data.mapper

import com.gulderbone.core.domain.AuthInfo
import javax.inject.Inject

internal class AuthInfoSerializableMapper @Inject constructor() {

    fun from(authInfoSerializable: AuthInfo): AuthInfo = with(authInfoSerializable) {
        AuthInfo(
            userId = userId
        )
    }
}