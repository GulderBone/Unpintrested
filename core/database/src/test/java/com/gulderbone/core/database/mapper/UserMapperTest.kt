package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.UserEntity
import com.gulderbone.core.domain.user.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserMapperTest {

    private val userMapper = UserMapper()

    @Test
    fun `maps from user-entity`() {
        // GIVEN
        val fakeId = 1L
        val fakeLogin = "testedLogin"
        val fakePassword = "testedPassword"

        val userEntity = UserEntity(
            id = fakeId,
            login = fakeLogin,
            password = fakePassword
        )

        // WHEN
        val user = userMapper.from(userEntity)

        // THEN
        val expected = User(
            id = fakeId,
            login = fakeLogin,
            password = fakePassword
        )
        assertEquals(expected, user)
    }
}