package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.domain.pin.Pin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PinMapperTest {

    private val mapper = PinMapper()

    @Test
    fun `maps from entity`() {
        // GIVEN
        val fakeName = "testedName"
        val fakePin: Long = 1
        val fakeUserId: Long = 2
        val entity = PinEntity(
            name = fakeName,
            value = fakePin,
            userId = fakeUserId,
        )

        // WHEN
        val pin = mapper.from(entity)

        // THEN
        val expected = Pin(
            name = fakeName,
            value = fakePin,
            userId = fakeUserId,
        )
        assertEquals(expected, pin)
    }
}