package com.gulderbone.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.gulderbone.core.database.entity.PinEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PinDao {

    @Upsert
    suspend fun upsertPin(pin: PinEntity)

    @Query("SELECT * FROM pinentity ORDER BY id DESC")
    fun getPins(): Flow<List<PinEntity>>

    @Query("DELETE FROM pinentity WHERE id=:id")
    suspend fun deletePin(id: Long)
}