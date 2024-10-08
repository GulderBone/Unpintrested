package com.gulderbone.unpintrested

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnpintrestedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationRoot(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}