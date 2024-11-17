package com.gulderbone.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulderbone.auth.domain.AuthRepository
import com.gulderbone.auth.presentation.R
import com.gulderbone.core.domain.util.AuthError
import com.gulderbone.core.domain.util.Result
import com.gulderbone.core.presentation.ui.MutableEventFlow
import com.gulderbone.core.presentation.ui.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    var state by mutableStateOf(RegisterState())
        private set

    private val _events = MutableEventFlow<RegisterEvent>()
    val events = _events.asSharedFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnLoginChange -> {
                state = state.copy(login = action.login)
            }

            is RegisterAction.OnPasswordChange -> {
                state = state.copy(password = action.password)
            }

            is RegisterAction.OnTogglePasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
            }
            is RegisterAction.OnRegisterClick -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            val result = repository.register(
                login = state.login.trim(),
                password = state.password
            )

            when (result) {
                is Result.Error -> {
                    when (result.error) {
                        AuthError.Conflict -> {
                            _events.emit(RegisterEvent.Error(UiText.StringResource(R.string.error_login_exists)))
                        }

                        AuthError.Unknown -> {
                            _events.emit(RegisterEvent.Error(UiText.StringResource(R.string.could_not_register_unknown_error)))
                        }
                    }
                }

                is Result.Success -> {
                    _events.emit(RegisterEvent.RegistrationSuccess)
                }
            }
        }
    }
}
