package com.gulderbone.pin.presentation.feature.addpin

import com.gulderbone.core.presentation.ui.UiText

sealed interface AddPinEvent {
    data class Error(val error: UiText): AddPinEvent
    data class PinAdded(val pinName: String): AddPinEvent
    data object Exit : AddPinEvent
}