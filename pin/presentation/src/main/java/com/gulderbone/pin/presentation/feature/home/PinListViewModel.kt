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
    PinUi(5, "Pin name five", "* * * * * *", false),
    PinUi(6, "Pin name six", "* * * * * *", false),
    PinUi(7, "Pin name seven", "* * * * * *", false),
    PinUi(8, "Pin name eight", "* * * * * *", false),
    PinUi(9, "Pin name nine", "* * * * * *", false),
    PinUi(10, "Pin name ten", "* * * * * *", false),
    PinUi(11, "Pin name eleven", "* * * * * *", false),
    PinUi(12, "Pin name twelve", "* * * * * *", false),
    PinUi(13, "Pin name thirteen", "* * * * * *", false),
    PinUi(14, "Pin name fourteen", "* * * * * *", false),
    PinUi(15, "Pin name fifteen", "* * * * * *", false),
    PinUi(16, "Pin name sixteen", "* * * * * *", false),
)