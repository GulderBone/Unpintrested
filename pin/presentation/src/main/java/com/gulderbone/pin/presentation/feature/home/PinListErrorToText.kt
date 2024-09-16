package com.gulderbone.pin.presentation.feature.home

import com.gulderbone.core.presentation.ui.UiText
import com.gulderbone.pin.presentation.R

fun PinListError.asUiText(): UiText {
    return when (this) {
        PinListError.DeletingFailed -> UiText.StringResource(
            R.string.deleting_failed
        )
    }
}