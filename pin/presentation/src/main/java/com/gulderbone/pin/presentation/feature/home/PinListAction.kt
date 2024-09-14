package com.gulderbone.pin.presentation.feature.home

sealed interface PinListAction {
    data object AddNewPin : PinListAction
    data class PinVisibilityChanged(val pinId: Int, val isVisible: Boolean) : PinListAction
}