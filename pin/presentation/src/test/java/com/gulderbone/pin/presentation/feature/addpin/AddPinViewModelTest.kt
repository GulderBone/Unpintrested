package com.gulderbone.pin.presentation.feature.addpin

import app.cash.turbine.test
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.Result
import com.gulderbone.core.presentation.ui.UiText
import com.gulderbone.pin.domain.PinGenerator
import com.gulderbone.pin.presentation.MainCoroutineExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
internal class AddPinViewModelTest {

    private val mockPinRepository = mockk<PinRepository>()
    private val mockPinGenerator = mockk<PinGenerator>()

    @Test
    fun `generates a pin on init`() {
        // GIVEN
        val fakePin = 123456L
        every { mockPinGenerator.generate() } returns fakePin

        // WHEN
        val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)

        // THEN
        assertEquals(fakePin, viewModel.state.pin)
    }

    @Nested
    inner class OnAction {

        @Test
        fun `updates state on pin-name-change`() {
            // GIVEN
            val fakePin = 123456L
            val newName = "new name"

            every { mockPinGenerator.generate() } returns fakePin

            // WHEN
            val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)
            viewModel.onAction(AddPinAction.OnPinNameChange(newName))

            // THEN
            assertEquals(newName, viewModel.state.name)
        }

        @Test
        fun `saves pin on on-add-pin-click`() = runTest {
            // GIVEN
            val newName = "testedName"
            val fakeValue = 123456L
            val fakePin = Pin(name = newName, value = fakeValue)

            every { mockPinGenerator.generate() } returns fakeValue
            coEvery { mockPinRepository.insertPin(fakePin) } returns Result.Success(Unit)

            // WHEN
            val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)
            viewModel.onAction(AddPinAction.OnPinNameChange(newName))
            viewModel.onAction(AddPinAction.OnAddPinClick)

            // THEN
            coVerify { mockPinRepository.insertPin(fakePin) }
        }

        @Test
        fun `sends pin-added event on successful on-add-pin-click`() = runTest {
            // GIVEN
            val newName = "testedName"
            val fakePin = 123456L

            every { mockPinGenerator.generate() } returns fakePin
            coEvery { mockPinRepository.insertPin(any()) } returns Result.Success(Unit)

            // WHEN
            val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)
            viewModel.onAction(AddPinAction.OnPinNameChange(newName))
            viewModel.onAction(AddPinAction.OnAddPinClick)

            // THEN
            viewModel.events.test {
                assertEquals(AddPinEvent.PinAdded(newName), awaitItem())
            }
        }

        @Test
        fun `sends error event on failed on-add-pin-click`() = runTest {
            // GIVEN
            val newName = "testedName"
            val fakePin = 123456L
            val mockUiText = mockk<UiText>()

            every { mockPinGenerator.generate() } returns fakePin
            coEvery { mockPinRepository.insertPin(any()) } returns Result.Error(DatabaseError.AlreadyExists)

            mockkStatic(AddPinError::asUiText)
            every { AddPinError.PinAlreadyExists.asUiText() } returns mockUiText

            // WHEN
            val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)
            viewModel.onAction(AddPinAction.OnPinNameChange(newName))
            viewModel.onAction(AddPinAction.OnAddPinClick)

            // THEN
            viewModel.events.test {
                assertEquals(AddPinEvent.Error(mockUiText), awaitItem())
            }
        }

        @Test
        fun `sends exit event on on-exit`() = runTest {
            // GIVEN
            val newName = "testedName"
            val fakePin = 123456L

            every { mockPinGenerator.generate() } returns fakePin

            // WHEN
            val viewModel = AddPinViewModel(mockPinRepository, mockPinGenerator)
            viewModel.onAction(AddPinAction.OnPinNameChange(newName))
            viewModel.onAction(AddPinAction.OnExit)

            // THEN
            viewModel.events.test {
                assertEquals(AddPinEvent.Exit, awaitItem())
            }
        }
    }
}