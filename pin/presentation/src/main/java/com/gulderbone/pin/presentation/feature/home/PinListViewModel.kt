package com.gulderbone.pin.presentation.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PinListViewModel : ViewModel() {

    var state by mutableStateOf(PinListState(pins = initialPins))
        private set

    fun onAction(action: PinListAction) {
        when (action) {
            PinListAction.AddNewPin -> {}
            is PinListAction.PinVisibilityChanged -> {}
        }
    }
}

val initialPins = listOf(
    PinUi(1, "Pin name one", "1 2 3 4 5 6", true),
    PinUi(2, "Pin name two", "4 2 0 6 9", true),
    PinUi(3, "Pin name three", "* * * * * *", false),
    PinUi(4, "Pin name four", "* * * * * *", false),
)