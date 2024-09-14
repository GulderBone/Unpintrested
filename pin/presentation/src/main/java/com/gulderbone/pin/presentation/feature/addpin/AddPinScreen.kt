package com.gulderbone.pin.presentation.feature.addpin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulderbone.core.presentation.designsystem.ThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme

@Composable
fun AddPinScreenRoot(
    viewModel: AddPinViewModel = AddPinViewModel(),
) {
    AddPinScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun AddPinScreen(
    state: AddPinState,
    onAction: (AddPinAction) -> Unit,
) {
    var pinName by rememberSaveable { mutableStateOf("") }
    var pinValue by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pinName,
            onValueChange = { pinName = it },
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pinValue.toString(),
            onValueChange = { pinValue = it.toInt() },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onAction(AddPinAction.onAddPinClick) }
        ) {
            Text("Add new pin")
        }
    }
}

@ThemePreviews
@Composable
private fun AddPinScreenPreview() {
    UnpintrestedTheme {
        AddPinScreen(
            state = AddPinState(),
            onAction = {}
        )
    }
}