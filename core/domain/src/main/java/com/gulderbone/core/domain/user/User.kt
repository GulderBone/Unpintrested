package com.gulderbone.core.domain.user

data class User(
    val id: Long,
    val login: String,
    val password: String,
)