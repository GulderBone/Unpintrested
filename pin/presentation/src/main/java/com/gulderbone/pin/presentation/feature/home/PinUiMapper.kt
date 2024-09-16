package com.gulderbone.pin.presentation.feature.home

import com.gulderbone.core.domain.pin.Pin
import javax.inject.Inject

class PinUiMapper @Inject constructor() {

    fun from(pin: Pin): PinUi = with(pin) {
        PinUi(
            name = name,
            pin = value.toString(),
            isMasked = false,
        )
    }
}