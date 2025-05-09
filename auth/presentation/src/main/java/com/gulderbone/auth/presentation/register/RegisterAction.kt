package com.gulderbone.auth.presentation.register

sealed interface RegisterAction {
    data class OnLoginChange(val login: String) : RegisterAction
    data class OnPasswordChange(val password: String) : RegisterAction
    data object OnTogglePasswordVisibilityClick : RegisterAction
    data object OnRegisterClick : RegisterAction
}
