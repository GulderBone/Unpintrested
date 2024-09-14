package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.domain.pin.Pin
import javax.inject.Inject

internal class PinEntityMapper @Inject constructor() {

    fun from(pin: Pin): PinEntity = with(pin) {
        PinEntity(
            name = name,
            value = value,
            id = id ?: 0L,
        )
    }
}