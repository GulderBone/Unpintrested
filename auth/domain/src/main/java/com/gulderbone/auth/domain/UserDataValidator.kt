package com.gulderbone.auth.domain

interface UserDataValidator {

    fun validatePassword(password: String): PasswordValidationState
}