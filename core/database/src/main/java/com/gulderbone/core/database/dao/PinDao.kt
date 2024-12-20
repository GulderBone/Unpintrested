package com.gulderbone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gulderbone.core.database.entity.PinEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface PinDao {

    @Query("SELECT * FROM pinentity WHERE LOWER(name) = LOWER(:name)")
    suspend fun getPin(name: String): PinEntity?

    @Query("SELECT * FROM pinentity ORDER BY name ASC")
    fun getPins(): Flow<List<PinEntity>>

    @Query("SELECT * FROM pinentity WHERE userId = :userId ORDER BY name ASC")
    fun getUserPins(userId: Long): Flow<List<PinEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPin(pin: PinEntity)

    @Query("DELETE FROM pinentity WHERE name = :name")
    suspend fun deletePin(name: String)
}