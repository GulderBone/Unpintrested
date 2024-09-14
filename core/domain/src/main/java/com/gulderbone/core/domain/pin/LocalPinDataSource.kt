package com.gulderbone.core.domain.pin

import kotlinx.coroutines.flow.Flow

interface LocalPinDataSource {
    fun getPins(): Flow<List<Pin>>
    suspend fun upsertPin(pin: Pin): Result<Unit>
    suspend fun deletePin(id: Long)
}