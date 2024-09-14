package com.gulderbone.core.data.pin

import com.gulderbone.core.domain.pin.LocalPinDataSource
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.pin.PinRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class OfflinePinRepository @Inject constructor(
    private val localPinDataSource: LocalPinDataSource,
    private val applicationScope: CoroutineScope,
) : PinRepository {

    override fun getPins(): Flow<List<Pin>> = localPinDataSource.getPins()

    override suspend fun upsertPin(pin: Pin) {
        applicationScope.launch {
            localPinDataSource.upsertPin(pin)
        }
    }

    override suspend fun deletePin(id: Long) {
        applicationScope.launch {
            localPinDataSource.deletePin(id)
        }
    }
}