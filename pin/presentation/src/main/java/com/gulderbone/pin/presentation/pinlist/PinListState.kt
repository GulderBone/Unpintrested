package com.gulderbone.pin.presentation.pinlist

data class PinListState(
    val pins: List<PinUi> = emptyList(),
)

data class PinUi(
    val id: String,
    val title: String,
)