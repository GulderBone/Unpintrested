package com.gulderbone.pin.presentation.feature.addpin

sealed interface AddPinAction {

    data object onAddPinClick : AddPinAction
}