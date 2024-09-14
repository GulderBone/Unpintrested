package com.gulderbone.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class PinEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val value: Int,
)