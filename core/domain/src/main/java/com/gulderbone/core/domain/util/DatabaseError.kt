package com.gulderbone.core.domain.util

sealed interface DatabaseError: Error {

    data object AlreadyExists: DatabaseError
}