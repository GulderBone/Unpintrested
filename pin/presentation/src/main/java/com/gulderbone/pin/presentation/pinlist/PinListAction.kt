package com.gulderbone.pin.presentation.pinlist

sealed interface PinListAction {
    data object AddNewPin : PinListAction
    data class PinVisibilityChanged(val pinId: Int, val isVisible: Boolean) : PinListAction
}