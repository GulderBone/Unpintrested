package com.gulderbone.core.domain.pin

import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow

interface PinRepository {
    fun getPins(): Flow<List<Pin>>
    suspend fun insertPin(pin: Pin): EmptyResult<DatabaseError>
    suspend fun deletePin(name: String): EmptyResult<DatabaseError>
}