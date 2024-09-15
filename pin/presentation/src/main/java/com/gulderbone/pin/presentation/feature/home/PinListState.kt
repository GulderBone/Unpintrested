package com.gulderbone.pin.presentation.feature.home

data class PinListState(
    val pins: List<PinUi> = emptyList(),
)

data class PinUi(
    val name: String,
    val pin: String, // TODO: do not keep as string for security reasons
    val isVisible: Boolean = false,
)