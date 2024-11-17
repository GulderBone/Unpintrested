package com.gulderbone.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
internal data class PinEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val value: Long,
    val userId: Long,
)