package com.gulderbone.pin.data.di

import com.gulderbone.pin.data.RandomSixDigitPinGenerator
import com.gulderbone.pin.domain.PinGenerator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindPinGenerator(impl: RandomSixDigitPinGenerator): PinGenerator
}