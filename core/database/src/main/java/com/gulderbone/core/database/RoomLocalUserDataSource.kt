package com.gulderbone.core.database

import com.gulderbone.core.database.dao.UserDao
import com.gulderbone.core.database.mapper.UserEntityMapper
import com.gulderbone.core.database.mapper.UserMapper
import com.gulderbone.core.domain.user.LocalUserDataSource
import com.gulderbone.core.domain.user.User
import com.gulderbone.core.domain.util.DatabaseError
import com.gulderbone.core.domain.util.EmptyResult
import com.gulderbone.core.domain.util.Result
import javax.inject.Inject

internal class RoomLocalUserDataSource @Inject constructor(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
    private val userEntityMapper: UserEntityMapper,
) : LocalUserDataSource {

    override suspend fun getUser(): User {
        val user = userDao.getUserByLogin("login")


        return userMapper.from(user ?: throw Exception())
    }

    override suspend fun insertUser(user: User): EmptyResult<DatabaseError> {
        return Result.Success(
            userDao.insertUser(
                userEntityMapper.from(user)
            )
        )
    }
}