package com.gulderbone.core.database.mapper

import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.domain.pin.Pin
import javax.inject.Inject

internal class PinMapper @Inject constructor() {

    fun from(entity: PinEntity) = with(entity) {
        Pin(
            id = id,
            name = name,
            value = value,
        )
    }
}
