package com.gulderbone.pin.presentation.feature.home

sealed interface PinListAction {
    data object AddNewPinClicked : PinListAction
    data class DeletePinClicked(val pinName: String) : PinListAction
    data class PinVisibilityChanged(val pinName: String, val isVisible: Boolean) : PinListAction
}