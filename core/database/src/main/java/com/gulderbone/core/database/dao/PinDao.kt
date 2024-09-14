package com.gulderbone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gulderbone.core.database.entity.PinEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PinDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPin(pin: PinEntity)

    @Query("SELECT * FROM pinentity ORDER BY name DESC")
    fun getPins(): Flow<List<PinEntity>>

    @Query("DELETE FROM pinentity WHERE name = :name")
    suspend fun deletePin(name: String)
}