package com.gulderbone.pin.presentation.feature.home

import app.cash.turbine.test
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.Result
import com.gulderbone.pin.presentation.MainCoroutineExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
internal class PinListViewModelTest {

    private val mockPinRepository = mockk<PinRepository>()
    private val mockPinUiMapper = mockk<PinUiMapper>()

    @Test
    fun `gets pins on init`() {
        // GIVEN
        val mockPin = mockk<Pin>()
        val mockPin2 = mockk<Pin>()
        val mockPinUi = mockk<PinUi>()
        val mockPinUi2 = mockk<PinUi>()

        every { mockPinUiMapper.from(mockPin) } returns mockPinUi
        every { mockPinUiMapper.from(mockPin2) } returns mockPinUi2

        every { mockPinRepository.getPins() } returns flowOf(listOf(mockPin, mockPin2))

        // WHEN
        val viewModel = PinListViewModel(mockPinRepository, mockPinUiMapper)

        // THEN
        assertEquals(listOf(mockPinUi, mockPinUi2), viewModel.state.pins)
    }

    @Nested
    inner class OnAction {

        @Test
        fun `sends pin-added event on add-new-pin-clicked`() = runTest {
            // GIVEN
            every { mockPinRepository.getPins() } returns emptyFlow()
            val viewModel = PinListViewModel(mockPinRepository, mockPinUiMapper)

            // WHEN
            viewModel.onAction(PinListAction.AddNewPinClicked)

            // THEN
            viewModel.events.test {
                assertEquals(PinListEvent.PinAdded, awaitItem())
            }
        }

        @Test
        fun `updates state on pin-visibility-changed`() {
            // GIVEN
            val fakeName = "testedName"
            val fakePin = "testedPin"
            val fakePinUi = PinUi(
                name = fakeName,
                pin = fakePin,
                isMasked = false
            )

            every { mockPinRepository.getPins() } returns flowOf(listOf(mockk()))
            every { mockPinUiMapper.from(any()) } returns fakePinUi

            val viewModel = PinListViewModel(mockPinRepository, mockPinUiMapper)

            // WHEN
            viewModel.onAction(PinListAction.PinVisibilityChanged(fakeName, true))

            // THEN
            assertEquals(
                listOf(
                    PinUi(
                        name = fakeName,
                        pin = fakePin,
                        isMasked = true
                    ),
                ),
                viewModel.state.pins
            )
        }

        @Test
        fun `deletes pin on delete-pin-clicked`() = runTest {
            // GIVEN
            val fakeName = "testedName"
            val fakePin = "testedPin"
            val fakePinUi = PinUi(
                name = fakeName,
                pin = fakePin,
                isMasked = false
            )

            every { mockPinRepository.getPins() } returns flowOf(listOf(mockk()))
            every { mockPinUiMapper.from(any()) } returns fakePinUi
            coEvery { mockPinRepository.deletePin(fakeName) } returns Result.Success(Unit)

            val viewModel = PinListViewModel(mockPinRepository, mockPinUiMapper)

            // WHEN
            viewModel.onAction(PinListAction.DeletePinClicked(fakeName))

            // THEN
            assertEquals(emptyList<PinUi>(), viewModel.state.pins)
        }
    }
}