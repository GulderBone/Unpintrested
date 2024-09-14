package com.gulderbone.core.database

import android.database.sqlite.SQLiteConstraintException
import com.gulderbone.core.database.dao.PinDao
import com.gulderbone.core.database.mapper.PinEntityMapper
import com.gulderbone.core.database.mapper.PinMapper
import com.gulderbone.core.domain.pin.LocalPinDataSource
import com.gulderbone.core.domain.pin.Pin
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.EmptyResult
import com.gulderbone.core.domain.util.Result
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

    override suspend fun insertPin(pin: Pin): EmptyResult<DatabaseError> = try {
        val entity = pinEntityMapper.from(pin)
        pinDao.insertPin(entity)
        Result.Success(Unit)
    } catch (e: SQLiteConstraintException) {
        Result.Error(DatabaseError.AlreadyExists)
    }

    override suspend fun deletePin(name: String): EmptyResult<DatabaseError> = try {
        pinDao.deletePin(name)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(DatabaseError.Unknown)
    }
}