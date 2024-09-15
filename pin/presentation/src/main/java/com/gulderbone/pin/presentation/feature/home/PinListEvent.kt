package com.gulderbone.pin.presentation.feature.home

sealed interface PinListEvent {
    data object PinAdded : PinListEvent
}