package com.gulderbone.pin.presentation.feature.home

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gulderbone.pin.presentation.R

@Composable
fun DeletePinDialog(
    pinName: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(stringResource(R.string.delete_pin, pinName)) },
        text = { Text(stringResource(R.string.delete_pin_confirmation)) },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                }
            ) {
                Text(stringResource(R.string.delete))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}