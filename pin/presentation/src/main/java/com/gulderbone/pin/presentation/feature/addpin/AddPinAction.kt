package com.gulderbone.pin.presentation.feature.addpin

sealed interface AddPinAction {

    // TODO: do not keep as LONG, potentially dangerous
    data class OnAddPinClick(val name: String, val value: Long) : AddPinAction
}