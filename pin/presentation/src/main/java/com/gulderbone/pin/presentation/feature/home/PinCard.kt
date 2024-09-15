package com.gulderbone.pin.presentation.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulderbone.core.presentation.designsystem.EyeClosedIcon
import com.gulderbone.core.presentation.designsystem.EyeOpenedIcon
import com.gulderbone.core.presentation.designsystem.ThemePreviews
import com.gulderbone.core.presentation.designsystem.TrashIcon
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme

@Composable
fun PinCard(
    name: String,
    value: String,
    isPinVisible: Boolean,
    onTogglePinVisibility: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val formattedPinValue = if (isPinVisible) {
        value.toCharArray().joinToString(" ")
    } else {
        "* * * * * *"
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(text = name)
            Text(text = formattedPinValue)
        }
        Row {
            IconButton(onClick = onTogglePinVisibility) {
                Icon(
                    imageVector = if (isPinVisible) {
                        EyeOpenedIcon
                    } else {
                        EyeClosedIcon
                    },
                    contentDescription = if (isPinVisible) {
                        "Hide Pin"
                    } else {
                        "Show Pin"
                    },
                )
            }
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = TrashIcon,
                    contentDescription = "Delete Pin",
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun PinCardPreview() {
    UnpintrestedTheme {
        PinCard(
            name = "Name",
            value = "123456",
            isPinVisible = true,
            onTogglePinVisibility = {},
            onDelete = {},
        )
    }
}