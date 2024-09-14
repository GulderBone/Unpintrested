package com.gulderbone.pin.presentation.feature.addpin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddPinViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(AddPinState())
        private set

    fun onAction(action: AddPinAction) {
        when (action) {
            else -> Unit
        }
    }
}