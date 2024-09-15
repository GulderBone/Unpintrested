package com.gulderbone.core.data.pin

import app.cash.turbine.test
import com.gulderbone.core.domain.pin.LocalPinDataSource
import com.gulderbone.core.domain.pin.Pin
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class OfflinePinRepositoryTest {

    private val mockLocalPinDataSource = mockk<LocalPinDataSource>()
    private val offlinePinRepository = OfflinePinRepository(
        localPinDataSource = mockLocalPinDataSource,
    )

    @Test
    fun `gets pins`() = runTest {
        // GIVEN
        val fakePin = Pin("testedName", 123456)
        val fakePin2 = Pin("testedName2", 654321)
        val fakePins = listOf(fakePin, fakePin2)
        every { mockLocalPinDataSource.getPins() } returns flowOf(fakePins)

        // WHEN & THEN
        offlinePinRepository.getPins().test {
            assertEquals(fakePins, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `inserts pin`() = runTest {
        // GIVEN
        val fakeName = "testedName"
        val fakeValue: Long = 123456
        val pin = Pin(fakeName, fakeValue)

        coJustRun { mockLocalPinDataSource.insertPin(pin) }

        // WHEN
        offlinePinRepository.insertPin(pin)

        // THEN
        coVerify { mockLocalPinDataSource.insertPin(pin) }
    }

    @Test
    fun `deletes pin`() = runTest {
        // GIVEN
        val fakeName = "testedName"
        coJustRun { mockLocalPinDataSource.deletePin(fakeName) }

        // WHEN
        offlinePinRepository.deletePin(fakeName)

        // THEN
        coVerify { mockLocalPinDataSource.deletePin(fakeName) }
    }
}