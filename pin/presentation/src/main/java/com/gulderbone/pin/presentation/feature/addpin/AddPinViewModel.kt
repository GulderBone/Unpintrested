package com.gulderbone.pin.presentation.feature.addpin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AddPinViewModel {

    var state by mutableStateOf(AddPinState())
        private set

    fun onAction(action: AddPinAction) {
        when (action) {
            else -> Unit
        }
    }
}