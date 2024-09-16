package com.gulderbone.pin.presentation.feature.addpin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.Result
import com.gulderbone.pin.domain.PinGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPinViewModel @Inject constructor(
    private val pinRepository: PinRepository,
    pinGenerator: PinGenerator,
) : ViewModel() {

    var state by mutableStateOf(
        AddPinState(pin = pinGenerator.generate())
    )
        private set

    private val eventChannel = Channel<AddPinEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: AddPinAction) {
        when (action) {
            is AddPinAction.OnPinNameChange -> {
                state = state.copy(
                    name = action.name,
                    canAdd = action.name.isNotBlank()
                )
            }

            is AddPinAction.OnAddPinClick -> {
                insertPin()
            }

            is AddPinAction.OnExit -> {
                viewModelScope.launch {
                    eventChannel.send(AddPinEvent.Exit)
                }
            }
        }
    }

    private fun insertPin() {
        viewModelScope.launch {
            val result = pinRepository.insertPin(
                Pin(
                    name = state.name,
                    value = state.pin,
                )
            )
            when (result) {
                is Result.Success -> eventChannel.send(AddPinEvent.PinAdded)
                is Result.Error -> {
                    val error = when (result.error) {
                        is DatabaseError.AlreadyExists -> AddPinError.PinAlreadyExists.asUiText()
                        else -> AddPinError.UnknownError.asUiText()
                    }
                    eventChannel.send(AddPinEvent.Error(error))
                }
            }
        }
    }
}