package com.gulderbone.pin.presentation.feature.home

sealed interface PinListError {

    data object DeletingFailed : PinListError
}