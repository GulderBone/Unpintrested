package com.gulderbone.auth.presentation.intro

sealed interface IntroAction {
    data object OnSignInClick: IntroAction
    data object OnSignUpClick: IntroAction
    data object OnContinueAsGuestClick: IntroAction
}