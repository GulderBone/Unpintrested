package com.gulderbone.pin.presentation.pinlist

data class PinListState(
    val pins: List<PinUi> = emptyList(),
)

data class PinUi(
    val id: Int,
    val name: String,
    val pin: String,
    val isVisible: Boolean = false,
)