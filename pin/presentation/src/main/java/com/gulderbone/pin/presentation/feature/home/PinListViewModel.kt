package com.gulderbone.pin.presentation.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinListViewModel @Inject constructor(
    private val pinRepository: PinRepository,
    private val pinUiMapper: PinUiMapper,
) : ViewModel() {

    var state by mutableStateOf(PinListState())
        private set

    init {
        viewModelScope.launch {
            pinRepository.insertPin(
                Pin(
                    name = "test",
                    value = 123456,
                )
            )
            pinRepository.insertPin(
                Pin(
                    name = "test2",
                    value = 420690,
                )
            )
            pinRepository.insertPin(
                Pin(
                    name = "test3",
                    value = 123456,
                )
            )
            pinRepository.getPins().collect { pins ->
                state = state.copy(pins = pins.map(pinUiMapper::from))
            }
        }
    }

    fun onAction(action: PinListAction) {
        when (action) {
            is PinListAction.PinVisibilityChanged -> {
                state = state.copy(pins = state.pins.map {
                    if (it.name == action.pinName) {
                        it.copy(isVisible = action.isVisible)
                    } else {
                        it
                    }
                })
            }
            is PinListAction.DeletePinClicked -> {
                onDeletePinClicked(action)
            }

            else -> {}
        }
    }

    private fun onDeletePinClicked(action: PinListAction.DeletePinClicked) {
        viewModelScope.launch {
            val result = pinRepository.deletePin(action.pinName)
            if (result is Result.Success) {
                state = state.copy(pins = state.pins.filter { it.name != action.pinName })
            }
        }
    }
}