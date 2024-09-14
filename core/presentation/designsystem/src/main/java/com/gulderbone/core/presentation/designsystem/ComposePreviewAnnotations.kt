package com.gulderbone.core.presentation.designsystem

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
annotation class ThemePreviews

@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES, heightDp = 640, widthDp = 360)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO, heightDp = 640, widthDp = 360)
annotation class ScreenThemePreviews