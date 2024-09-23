package com.gulderbone.auth.domain

import com.gulderbone.core.domain.util.EmptyResult
import com.gulderbone.core.domain.util.AuthError

interface AuthRepository {
    suspend fun login(login: String, password: String): EmptyResult<AuthError>
    suspend fun register(login: String, password: String): EmptyResult<AuthError>
}