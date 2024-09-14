package com.gulderbone.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class PinEntity(
    val name: String,
    val value: String,
    @PrimaryKey(autoGenerate = true)
    val id: Long,
)