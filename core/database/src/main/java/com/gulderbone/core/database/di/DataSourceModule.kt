package com.gulderbone.core.database.di

import com.gulderbone.core.database.RoomLocalPinDataSource
import com.gulderbone.core.domain.pin.LocalPinDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun bindRoomLocalPinDataSource(roomLocalPinDataSource: RoomLocalPinDataSource): LocalPinDataSource
}