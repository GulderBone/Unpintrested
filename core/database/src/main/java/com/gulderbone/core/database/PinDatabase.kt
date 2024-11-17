package com.gulderbone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gulderbone.core.database.dao.PinDao
import com.gulderbone.core.database.dao.UserDao
import com.gulderbone.core.database.entity.PinEntity
import com.gulderbone.core.database.entity.UserEntity

@Database(
    entities = [
        PinEntity::class,
        UserEntity::class,
    ], version = 1
)
internal abstract class PinDatabase : RoomDatabase() {

    abstract val pinDao: PinDao

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME: String = "pin_db"
    }
}