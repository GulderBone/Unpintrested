@file:OptIn(ExperimentalMaterial3Api::class)

package com.gulderbone.pin.presentation.feature.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.core.presentation.ui.ObserveAsEvents
import com.gulderbone.pin.presentation.R

@Composable
fun PinListScreenRoot(
    onAddPinClick: () -> Unit,
    viewModel: PinListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is PinListEvent.Error -> {
                displayErrorToast(context, event)
            }

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
    if (state.isDeleting) {
        DeletePinDialog(
            pinName = state.deletedPinName,
            onConfirm = { onAction(PinListAction.DeletePinConfirmed) },
            onDismiss = { onAction(PinListAction.DeletePinDismissed) }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(PinListAction.AddNewPinClicked)
                }) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_new_pin))
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.pins) { pin ->
                    PinCard(
                        name = pin.name,
                        value = pin.pin,
                        isPinVisible = pin.isMasked,
                        onTogglePinVisibility = { onAction(PinListAction.PinVisibilityChanged(pin.name, !pin.isMasked)) },
                        onDelete = { onAction(PinListAction.DeletePinClicked(pin.name)) },
                        modifier = Modifier.animateItem()
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
            }
        }
    }
}

private fun displayErrorToast(context: Context, event: PinListEvent.Error) {
    Toast.makeText(
        context,
        event.error.asString(context),
        Toast.LENGTH_LONG
    ).show()
}

@ScreenThemePreviews
@Composable
private fun PinListScreenPreview() {
    UnpintrestedTheme {
        PinListScreen(
            state = PinListState(
                pins = listOf(
                    PinUi("My Locker", "123456", false),
                    PinUi("Tinder", "123456", true),
                    PinUi("Secret", "123456", false),
                ),
            ),
            onAction = {}
        )
    }
}