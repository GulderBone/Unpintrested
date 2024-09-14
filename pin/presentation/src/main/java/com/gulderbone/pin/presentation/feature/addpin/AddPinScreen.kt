package com.gulderbone.pin.presentation.feature.addpin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.core.presentation.ui.ObserveAsEvents

@Composable
fun AddPinScreenRoot(
    onPinAdded: () -> Unit,
    viewModel: AddPinViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is AddPinEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.asString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
            is AddPinEvent.PinAdded -> {
                keyboardController?.hide()
                onPinAdded()
            }
        }
    }

    AddPinScreen(
        onAction = viewModel::onAction
    )
}

@Composable
private fun AddPinScreen(
    onAction: (AddPinAction) -> Unit,
) {
    var pinName by rememberSaveable { mutableStateOf("") }
    var pinValue by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pinName,
            onValueChange = { pinName = it },
            singleLine = true,
            label = { Text("Pin name") },
            placeholder = { Text("My locker pin") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )

        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = pinValue.toString(),
            onValueChange = { pinValue = it.toInt() },
            singleLine = true,
            label = { Text("Pin value") },
            placeholder = { Text("1 2 3 4 5 6") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                onAction(
                    AddPinAction.OnAddPinClick(
                        name = pinName,
                        value = pinValue,
                    )
                )
            }
        ) {
            Text("Add new pin")
        }
    }
}

@ScreenThemePreviews
@Composable
private fun AddPinScreenPreview() {
    UnpintrestedTheme {
        AddPinScreen(
            onAction = {}
        )
    }
}