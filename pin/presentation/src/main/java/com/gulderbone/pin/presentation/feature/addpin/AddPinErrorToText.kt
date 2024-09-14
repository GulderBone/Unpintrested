package com.gulderbone.pin.presentation.feature.addpin

import com.gulderbone.core.presentation.ui.UiText
import com.gulderbone.pin.presentation.R

fun AddPinError.asUiText(): UiText {
    return when (this) {
        AddPinError.PinAlreadyExists -> UiText.StringResource(
            R.string.pin_already_exists
        )
        AddPinError.UnknownError -> UiText.StringResource(
            R.string.unknown_error
        )
    }
}