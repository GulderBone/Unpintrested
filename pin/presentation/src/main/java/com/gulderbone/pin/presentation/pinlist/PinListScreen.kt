package com.gulderbone.pin.presentation.pinlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme

@Composable
fun PinListScreenRoot(
    viewModel: PinListViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {

    PinListScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )

}

@Composable
private fun PinListScreen(
    state: PinListState,
    onAction: () -> Unit
) {


}

@Preview
@Composable
private fun PinListScreenPreview() {
    UnpintrestedTheme {
        PinListScreen(
            state = PinListState(),
            onAction = {}
        )
    }
}