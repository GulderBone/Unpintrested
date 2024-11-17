package com.gulderbone.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gulderbone.core.database.entity.UserEntity

@Dao
internal interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM userentity WHERE login = :login")
    fun getUserByLogin(login: String): UserEntity?
}