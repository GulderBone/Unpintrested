package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.domain.pin.Pin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PinEntityMapperTest {

    private val mapper = PinEntityMapper()

    @Test
    fun `maps from pin`() {
        // GIVEN
        val fakeName = "testedName"
        val fakePin: Long = 123456
        val pin = Pin(
            name = fakeName,
            value = fakePin
        )

        // WHEN
        val entity = mapper.from(pin)

        // THEN
        val expected = PinEntity(
            name = fakeName,
            value = fakePin
        )
        assertEquals(expected, entity)
    }
}