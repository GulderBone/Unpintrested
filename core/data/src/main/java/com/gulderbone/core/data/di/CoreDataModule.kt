package com.gulderbone.core.data.di

import com.gulderbone.core.data.pin.OfflinePinRepository
import com.gulderbone.core.domain.pin.PinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreDataModule {

    @Binds
    abstract fun bindOfflinePinRepository(offlinePinRepository: OfflinePinRepository): PinRepository
}