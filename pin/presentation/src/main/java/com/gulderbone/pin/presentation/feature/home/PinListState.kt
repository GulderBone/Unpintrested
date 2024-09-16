package com.gulderbone.pin.presentation.feature.home

data class PinListState(
    val pins: List<PinUi> = emptyList(),
    val isDeleting: Boolean = false,
    val deletedPinName : String = "",
)
