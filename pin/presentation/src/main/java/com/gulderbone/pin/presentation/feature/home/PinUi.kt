package com.gulderbone.pin.presentation.feature.home

data class PinUi(
    val name: String,
    // TODO: Not safe, string is immutable and kept in the memory until replaced
    val pin: String,
    val isMasked: Boolean = false,
)