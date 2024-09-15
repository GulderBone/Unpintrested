package com.gulderbone.pin.presentation.feature.addpin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.core.presentation.ui.ObserveAsEvents
import com.gulderbone.pin.presentation.R

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
    val pinValue by rememberSaveable { mutableLongStateOf(state.pin) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.generated_pin_number),
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(4.dp))

        GeneratedPinUi(pinValue)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.name_your_new_pin),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = pinName,
            onValueChange = { pinName = it },
            singleLine = true,
            label = { Text(stringResource(R.string.pin_name_label)) },
            placeholder = { Text(stringResource(R.string.pin_name_placeholder)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onAction(
                        AddPinAction.OnAddPinClick(
                            name = pinName,
                            value = state.pin,
                        )
                    )
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onAction(
                    AddPinAction.OnAddPinClick(
                        name = pinName,
                        value = state.pin,
                    )
                )
            }
        ) {
            Text(stringResource(R.string.create_pin))
        }
    }
}

@Composable
private fun GeneratedPinUi(pinValue: Long) {
    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = pinValue.toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 4.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@ScreenThemePreviews
@Composable
private fun AddPinScreenPreview() {
    UnpintrestedTheme {
        AddPinScreen(
            state = AddPinState(pin = 123456),
            onAction = {}
        )
    }
}