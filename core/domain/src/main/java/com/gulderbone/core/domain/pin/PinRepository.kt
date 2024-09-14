package com.gulderbone.core.domain.pin

import kotlinx.coroutines.flow.Flow

interface PinRepository {
    fun getPins(): Flow<List<Pin>>
    suspend fun upsertPin(pin: Pin)
    suspend fun deletePin(id: Long)
}