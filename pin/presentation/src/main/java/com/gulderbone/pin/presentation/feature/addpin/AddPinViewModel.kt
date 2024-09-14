package com.gulderbone.pin.presentation.feature.addpin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPinViewModel @Inject constructor(
    private val pinRepository: PinRepository,
) : ViewModel() {

    private val eventChannel = Channel<AddPinEvent>()
    val events = eventChannel.receiveAsFlow()

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
}