package com.gulderbone.core.domain.user

import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.EmptyResult

interface LocalUserDataSource {
    suspend fun getUser(login: String): User?
    suspend fun insertUser(user: NewUser): EmptyResult<DatabaseError>
}