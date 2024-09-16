package com.gulderbone.pin.presentation.feature.home

import com.gulderbone.core.presentation.ui.UiText

sealed interface PinListEvent {
    data object PinAdded : PinListEvent
    data class Error(val error: UiText) : PinListEvent
}