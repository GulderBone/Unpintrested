package com.gulderbone.unpintrested

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.pin.presentation.pinlist.PinListScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnpintrestedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        PinListScreenRoot()
                    }
                }
            }
        }
    }
}

@ScreenThemePreviews
@Composable
fun MainScreenPreview() {
    UnpintrestedTheme {
        PinListScreenRoot()
    }
}