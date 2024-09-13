package com.gulderbone.pin.presentation.pinlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PinListViewModel : ViewModel() {

    var state by mutableStateOf(PinListState())

    fun onAction() {
        // Do something
    }
}