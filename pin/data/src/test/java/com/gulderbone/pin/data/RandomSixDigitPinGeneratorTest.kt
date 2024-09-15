package com.gulderbone.pin.data

import io.mockk.mockkStatic
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

internal class RandomSixDigitPinGeneratorTest {

    private val randomSixDigitPinGenerator = RandomSixDigitPinGenerator()

    @Test
    fun `generate should return a 6 digit number`() {
        // WHEN
        val pin = randomSixDigitPinGenerator.generate()

        mockkStatic(Random::class)

        // THEN
        assertTrue(pin in 100000..999999, "Pin should be a 6-digit number")
    }
}