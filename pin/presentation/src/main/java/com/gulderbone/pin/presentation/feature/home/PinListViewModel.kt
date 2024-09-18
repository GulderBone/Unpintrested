package com.gulderbone.pin.presentation.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.Result
import com.gulderbone.core.presentation.ui.MutableEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinListViewModel @Inject constructor(
    private val pinRepository: PinRepository,
    private val pinUiMapper: PinUiMapper,
) : ViewModel() {

    var state by mutableStateOf(PinListState())
        private set

    private val _events = MutableEventFlow<PinListEvent>()
    val events = _events.asSharedFlow()

    init {
        viewModelScope.launch {
            pinRepository.getPins().collect { pins ->
                state = state.copy(
                    pins = pins
                        .map(pinUiMapper::from)
                        .sortedBy { it.name.lowercase() }
                )
            }
        }
    }

    fun onAction(action: PinListAction) {
        when (action) {
            is PinListAction.AddNewPinClicked -> {
                viewModelScope.launch {
                    _events.emit((PinListEvent.PinAdded))
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
            state = when (result) {
                is Result.Success -> {
                    state.copy(
                        pins = state.pins.filter { it.name != pinName },
                        isDeleting = false,
                        deletedPinName = ""
                    )
                }

                is Result.Error -> {
                    _events.emit(PinListEvent.Error(PinListError.DeletingFailed.asUiText()))
                    state.copy(
                        isDeleting = false,
                        deletedPinName = ""
                    )
                }
            }
        }
    }
}