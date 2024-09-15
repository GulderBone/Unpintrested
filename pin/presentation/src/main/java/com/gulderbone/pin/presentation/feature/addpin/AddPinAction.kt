package com.gulderbone.pin.presentation.feature.addpin

sealed interface AddPinAction {

    data class OnPinNameChange(val name: String) : AddPinAction
    data object OnAddPinClick : AddPinAction
    data object OnExit: AddPinAction
}