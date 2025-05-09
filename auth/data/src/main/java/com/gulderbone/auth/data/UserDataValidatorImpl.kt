package com.gulderbone.auth.data

import com.gulderbone.auth.domain.PasswordValidationState
import com.gulderbone.auth.domain.UserDataValidator
import javax.inject.Inject

class UserDataValidatorImpl @Inject constructor(): UserDataValidator {

    override fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter,
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 9
    }
}