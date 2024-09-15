package com.gulderbone.pin.presentation.feature.addpin

data class AddPinState(
    val name: String = "",
    val pin: Long = 0L,
    val canAdd: Boolean = false,
)
