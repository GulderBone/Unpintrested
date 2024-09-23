package com.gulderbone.core.domain.util

interface AuthError: Error {
    data object Conflict: AuthError
    data object Unknown: AuthError
}