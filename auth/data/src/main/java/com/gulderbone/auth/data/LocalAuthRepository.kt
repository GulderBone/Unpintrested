package com.gulderbone.auth.data

import com.gulderbone.auth.domain.AuthRepository
import com.gulderbone.core.domain.util.AuthError
import com.gulderbone.core.domain.util.EmptyResult
import com.gulderbone.core.domain.util.Result
import com.gulderbone.core.domain.util.asEmptyDataResult
import javax.inject.Inject

class LocalAuthRepository @Inject constructor() : AuthRepository {


    // TODO
    override suspend fun login(login: String, password: String): EmptyResult<AuthError> {
        return Result.Success(Unit).asEmptyDataResult()
    }

    // TODO
    override suspend fun register(login: String, password: String): EmptyResult<AuthError> {
        return Result.Success(Unit).asEmptyDataResult()
    }
}