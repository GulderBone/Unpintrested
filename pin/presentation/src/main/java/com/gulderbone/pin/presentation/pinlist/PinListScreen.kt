package com.gulderbone.pin.presentation.pinlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme

@Composable
fun PinListScreenRoot(
    viewModel: PinListViewModel = PinListViewModel(),
) {
    PinListScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun PinListScreen(
    state: PinListState,
    onAction: (PinListAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = 32.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Pin(true, {})
            Pin(true, {})
            Pin(true, {})
            Pin(true, {})
            Pin(false, {})
            Pin(false, {})
        }
        Button(onClick = {}) {
            Text("Add new pin")
        }
    }

}

@ScreenThemePreviews
@Composable
private fun PinListScreenPreview() {
    UnpintrestedTheme {
        PinListScreen(
            state = PinListState(),
            onAction = {}
        )
    }
}