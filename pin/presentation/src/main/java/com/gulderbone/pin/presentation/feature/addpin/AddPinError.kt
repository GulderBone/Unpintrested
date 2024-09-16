package com.gulderbone.pin.presentation.feature.addpin

sealed interface AddPinError {

    data object EmptyName : AddPinError
    data object PinAlreadyExists : AddPinError
    data object UnknownError : AddPinError
}
