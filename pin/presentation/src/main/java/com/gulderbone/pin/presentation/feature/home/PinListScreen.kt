package com.gulderbone.pin.presentation.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.core.presentation.ui.ObserveAsEvents

@Composable
fun PinListScreenRoot(
    onAddPinClick: () -> Unit,
    viewModel: PinListViewModel = hiltViewModel(),
) {

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is PinListEvent.PinAdded -> {
                onAddPinClick()
            }
        }
    }

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
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.pins) { pin ->
                PinCard(
                    name = pin.name,
                    value = pin.pin,
                    isPinVisible = pin.isVisible,
                    onTogglePinVisibility = { onAction(PinListAction.PinVisibilityChanged(pin.name, !pin.isVisible)) },
                    onDelete = { onAction(PinListAction.DeletePinClicked(pin.name)) }
                )
            }
        }
        Button(
            onClick = {
                onAction(PinListAction.AddNewPinClicked)
            }) {
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