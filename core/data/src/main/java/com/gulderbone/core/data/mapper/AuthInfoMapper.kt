package com.gulderbone.core.data.mapper

import com.gulderbone.core.data.auth.AuthInfoSerializable
import com.gulderbone.core.domain.AuthInfo
import javax.inject.Inject

internal class AuthInfoMapper @Inject constructor() {

    fun from(authInfoSerializable: AuthInfoSerializable): AuthInfo = with(authInfoSerializable) {
        AuthInfo(
            userId = userId
        )
    }
}