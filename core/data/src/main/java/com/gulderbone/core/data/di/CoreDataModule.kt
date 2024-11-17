package com.gulderbone.core.data.di

import com.gulderbone.core.data.auth.EncryptedSessionStorage
import com.gulderbone.core.data.pin.OfflinePinRepository
import com.gulderbone.core.domain.SessionStorage
import com.gulderbone.core.domain.pin.PinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreDataModule {

    @Binds
    abstract fun bindEncryptedSessionStorage(encryptedSessionStorage: EncryptedSessionStorage): SessionStorage

    @Binds
    abstract fun bindOfflinePinRepository(offlinePinRepository: OfflinePinRepository): PinRepository
}