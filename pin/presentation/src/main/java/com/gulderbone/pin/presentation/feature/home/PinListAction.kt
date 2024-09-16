package com.gulderbone.pin.presentation.feature.home

sealed interface PinListAction {
    data class DeletePinClicked(val pinName: String) : PinListAction
    data object DeletePinConfirmed : PinListAction
    data object DeletePinDismissed : PinListAction
    data class PinVisibilityChanged(val pinName: String, val isVisible: Boolean) : PinListAction
    data object AddNewPinClicked : PinListAction
}