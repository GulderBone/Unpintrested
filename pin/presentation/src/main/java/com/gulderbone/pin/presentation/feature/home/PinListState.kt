package com.gulderbone.pin.presentation.feature.home

data class PinListState(
    val pins: List<PinUi> = emptyList(),
)

data class PinUi(
    val name: String,
    val pin: String,
    val isVisible: Boolean = false,
)