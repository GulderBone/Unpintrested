package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.UserEntity
import com.gulderbone.core.domain.user.User
import javax.inject.Inject

internal class UserEntityMapper @Inject constructor() {

    fun from(user: User): UserEntity = with(user) {
        UserEntity(
            id = id,
            login = login,
            password = password,
        )
    }
}