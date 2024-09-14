package com.gulderbone.pin.presentation.feature.addpin

import androidx.compose.foundation.text.input.TextFieldState

data class AddPinState(
    val name: TextFieldState = TextFieldState(),
    val pin: TextFieldState = TextFieldState(),
)