package com.gulderbone.pin.presentation.pinlist

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Pin(
    isPinVisible: Boolean,
    onTogglePinVisibility: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Text(text = "Pin name one")
            Text(text = "* * * * * *")
        }
        IconButton(onClick = onTogglePinVisibility) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = if (isPinVisible) {
                    com.gulderbone.core.presentation.designsystem.EyeOpenedIcon
                } else {
                    com.gulderbone.core.presentation.designsystem.EyeClosedIcon
                },
                contentDescription = "Arrow Forward",
            )
        }
    }
}