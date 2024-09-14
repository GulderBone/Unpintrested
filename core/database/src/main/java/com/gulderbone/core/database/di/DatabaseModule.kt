package com.gulderbone.core.database.di

import android.app.Application
import androidx.room.Room
import com.gulderbone.core.database.PinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providePinDatabase(app: Application): PinDatabase {
        return Room.databaseBuilder(
            app,
            PinDatabase::class.java,
            PinDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providePinDao(pinDatabase: PinDatabase) = pinDatabase.pinDao
}