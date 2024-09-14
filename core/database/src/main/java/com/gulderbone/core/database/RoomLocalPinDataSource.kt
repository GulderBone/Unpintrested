package com.gulderbone.core.database

import com.gulderbone.core.database.dao.PinDao
import com.gulderbone.core.database.mapper.PinEntityMapper
import com.gulderbone.core.database.mapper.PinMapper
import com.gulderbone.core.domain.pin.LocalPinDataSource
import com.gulderbone.core.domain.pin.Pin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RoomLocalPinDataSource @Inject constructor(
    private val pinDao: PinDao,
    private val pinMapper: PinMapper,
    private val pinEntityMapper: PinEntityMapper,
) : LocalPinDataSource {

    override fun getPins(): Flow<List<Pin>> = pinDao
        .getPins()
        .map { pins ->
            pins.map(pinMapper::from)
        }

    override suspend fun upsertPin(pin: Pin): Result<Unit> = try {
        val entity = pinEntityMapper.from(pin)
        pinDao.upsertPin(entity)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deletePin(id: Long) = pinDao.deletePin(id)
}