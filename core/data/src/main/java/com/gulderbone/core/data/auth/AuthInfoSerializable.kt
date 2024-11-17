package com.gulderbone.core.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val userId: Long,
)
