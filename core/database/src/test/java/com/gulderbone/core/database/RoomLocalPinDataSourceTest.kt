package com.gulderbone.core.database

import app.cash.turbine.test
import com.gulderbone.core.database.dao.PinDao
import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.database.mapper.PinEntityMapper
import com.gulderbone.core.database.mapper.PinMapper
import com.gulderbone.core.domain.pin.Pin
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RoomLocalPinDataSourceTest {

    private val mockPinDao = mockk<PinDao>()
    private val mockPinMapper = mockk<PinMapper>()
    private val mockPinEntityMapper = mockk<PinEntityMapper>()

    private val roomLocalPinDataSource = RoomLocalPinDataSource(
        pinDao = mockPinDao,
        pinMapper = mockPinMapper,
        pinEntityMapper = mockPinEntityMapper,
    )

    @Test
    fun `gets pins`() = runTest {
        // GIVEN
        val mockPinEntity = mockk<PinEntity>()
        val mockPinEntity2 = mockk<PinEntity>()
        val mockPinEntities = listOf(mockPinEntity, mockPinEntity2)

        val mockPin = mockk<Pin>()
        val mockPin2 = mockk<Pin>()
        val mockPins = listOf(mockPin, mockPin2)

        every { mockPinDao.getPins() } returns flowOf(mockPinEntities)
        every { mockPinMapper.from(mockPinEntity) } returns mockPin
        every { mockPinMapper.from(mockPinEntity2) } returns mockPin2

        // WHEN & THEN
        roomLocalPinDataSource.getPins().test {
            assertEquals(mockPins, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}