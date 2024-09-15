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
    private val pinGenerator: PinGenerator,
) : ViewModel() {

    var state by mutableStateOf(AddPinState())
        private set

    private val eventChannel = Channel<AddPinEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        state = state.copy(pin = generatePin())
    }

    fun onAction(action: AddPinAction) {
        when (action) {
            is AddPinAction.OnAddPinClick -> {
                viewModelScope.launch {
                    val result = pinRepository.insertPin(
                        Pin(
                            name = action.name,
                            value = action.value,
                        )
                    )
                    when(result) {
                        is Result.Success -> eventChannel.send(AddPinEvent.PinAdded)
                        is Result.Error -> {
                            val error = when(result.error) {
                                is DatabaseError.AlreadyExists -> AddPinError.PinAlreadyExists.asUiText()
                                else -> AddPinError.UnknownError.asUiText()
                            }
                            eventChannel.send(AddPinEvent.Error(error))
                        }
                    }
                }
            }
        }
    }

    private fun generatePin(): Long = pinGenerator.generate()
}