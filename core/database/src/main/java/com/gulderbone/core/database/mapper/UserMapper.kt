package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.UserEntity
import com.gulderbone.core.domain.user.User
import javax.inject.Inject

internal class UserMapper @Inject constructor() {

    fun from(user: UserEntity): User = with(user) {
        User(
            id = id,
            login = login,
            password = password,
        )
    }
}