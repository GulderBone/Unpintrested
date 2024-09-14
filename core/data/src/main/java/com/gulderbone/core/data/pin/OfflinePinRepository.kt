package com.gulderbone.core.data.pin

import com.gulderbone.core.domain.pin.LocalPinDataSource
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflinePinRepository @Inject constructor(
    private val localPinDataSource: LocalPinDataSource,
) : PinRepository {

    override fun getPins(): Flow<List<Pin>> = localPinDataSource.getPins()

    override suspend fun insertPin(pin: Pin): EmptyResult<DatabaseError> = localPinDataSource.insertPin(pin)

    override suspend fun deletePin(name: String): EmptyResult<DatabaseError> = localPinDataSource.deletePin(name)
}