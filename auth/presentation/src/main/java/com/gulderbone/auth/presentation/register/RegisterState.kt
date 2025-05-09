package com.gulderbone.auth.presentation.register

data class RegisterState(
    val login: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val canRegister: Boolean = false,
)
