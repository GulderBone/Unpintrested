package com.gulderbone.auth.data.di

import com.gulderbone.auth.data.LocalAuthRepository
import com.gulderbone.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindAuthRepository(impl: LocalAuthRepository): AuthRepository
}