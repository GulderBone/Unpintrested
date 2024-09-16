package com.gulderbone.pin.presentation.feature.home

import com.gulderbone.core.domain.pin.Pin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PinUiMapperTest {

    private val pinUiMapper = PinUiMapper()

    @Test
    fun `maps from pin`() {
        // GIVEN
        val fakeName = "testedName"
        val fakeValue = 123456L
        val pin = Pin(
            name = fakeName,
            value = fakeValue,
        )

        // WHEN
        val result = pinUiMapper.from(pin)

        // THEN
        val expected = PinUi(
            name = fakeName,
            pin = fakeValue.toString(),
            isMasked = false,
        )
        assertEquals(expected, result)
    }
}