package com.gulderbone.unpintrested

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gulderbone.auth.presentation.intro.IntroScreenRoot
import com.gulderbone.auth.presentation.register.RegisterScreenRoot
import com.gulderbone.pin.presentation.feature.addpin.AddPinScreenRoot
import com.gulderbone.pin.presentation.feature.home.PinListScreenRoot
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Intro
    ) {
        composable<Intro> {
            IntroScreenRoot(
                onSignUpClick = {
                     navController.navigate(Register)
                },
                onSignInClick = {
//                     navController.navigate(Login)
                },
                onContinueAsGuestClick = {
                    navController.navigate(Home)
                }
            )
        }
        composable<Register> {
            RegisterScreenRoot(
                onSuccessfulRegistration = {
//                    navController.navigate(Login)
                }
            )
        }
        composable<Home> {
            PinListScreenRoot(
                onAddPinClick = {
                    navController.navigate(AddPin)
                }
            )
        }
        composable<AddPin> {
            AddPinScreenRoot(
                onExit = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Serializable
object Intro

@Serializable
object Register

//@Serializable
//object Login

@Serializable
object Home

@Serializable
object AddPin