package com.gulderbone.auth.presentation.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gulderbone.auth.presentation.R
import com.gulderbone.core.presentation.designsystem.ScreenThemePreviews
import com.gulderbone.core.presentation.designsystem.UnpintrestedTheme
import com.gulderbone.core.presentation.designsystem.components.GradientBackground

@Composable
fun IntroScreenRoot(
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
    onContinueAsGuestClick: () -> Unit,
) {
    IntroScreen(
        onAction = { action ->
            when (action) {
                IntroAction.OnSignInClick -> onSignInClick()
                IntroAction.OnSignUpClick -> onSignUpClick()
                IntroAction.OnContinueAsGuestClick -> onContinueAsGuestClick()
            }
        }
    )
}

@Composable
fun IntroScreen(
    onAction: (IntroAction) -> Unit,
) {
    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(128.dp),
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Logo",
                        alignment = Alignment.Center
                    )
                    Text(
                        text = stringResource(R.string.unpintrested),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Text(
                text = stringResource(R.string.welcome_to_unpintrested),
                fontSize = 20.sp,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.unpintrested_description),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.size(32.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { IntroAction.OnSignInClick }) {
                Text(
                    text = stringResource(R.string.sign_up),
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { IntroAction.OnSignUpClick }) {
                Text(
                    text = stringResource(R.string.sign_in),
                )
            }

            Spacer(modifier = Modifier.size(32.dp))
            Text(
                text = stringResource(R.string.continue_as_guest),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAction(IntroAction.OnContinueAsGuestClick) },
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@ScreenThemePreviews
@Composable
private fun IntroScreenPreview() {
    UnpintrestedTheme {
        IntroScreen {
        }
    }
}