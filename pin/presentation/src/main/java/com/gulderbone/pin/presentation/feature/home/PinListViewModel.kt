package com.gulderbone.pin.presentation.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinListViewModel @Inject constructor(
    private val pinRepository: PinRepository,
    private val pinUiMapper: PinUiMapper,
) : ViewModel() {

    var state by mutableStateOf(PinListState())
        private set

    private val eventChannel = Channel<PinListEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            pinRepository.getPins().collect { pins ->
                state = state.copy(pins = pins.map(pinUiMapper::from))
            }
        }
    }

    fun onAction(action: PinListAction) {
        when (action) {
            is PinListAction.AddNewPinClicked -> {
                viewModelScope.launch {
                    eventChannel.send(PinListEvent.PinAdded)
                }
            }

            is PinListAction.DeletePinConfirmed -> {
                deletePin(state.deletedPinName)
            }

            is PinListAction.DeletePinDismissed -> {
                state = state.copy(
                    isDeleting = false,
                    deletedPinName = ""
                )
            }

            is PinListAction.PinVisibilityChanged -> {
                state = state.copy(pins = state.pins.map {
                    if (it.name == action.pinName) {
                        it.copy(isMasked = action.isVisible)
                    } else {
                        it
                    }
                })
            }

            is PinListAction.DeletePinClicked -> {
                state = state.copy(
                    isDeleting = true,
                    deletedPinName = action.pinName
                )
            }
        }
    }

    private fun deletePin(pinName: String) {
        viewModelScope.launch {
            val result = pinRepository.deletePin(pinName)
            if (result is Result.Success) {
                state = state.copy(pins = state.pins.filter { it.name != pinName })
            }
            state = state.copy(
                isDeleting = false,
                deletedPinName = ""
            )
        }
    }
}