package com.conectaedu.android.ui.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailTextChanged(text: String) {
        uiState = uiState.copy(email = text)
        validateEmail()
    }

    private fun validateEmail() {
        val pattern = Patterns.EMAIL_ADDRESS
        val isValidEmail = pattern.matcher(uiState.email).matches()
        uiState = uiState.copy(isValidEmail = isValidEmail)
    }

    fun onPasswordTextChanged(text: String) {
        uiState = uiState.copy(password = text)
        validatePassword()
    }

    private fun validatePassword() {
        val isValidPassword = uiState.password.length in 8..16
        uiState = uiState.copy(isValidPassword = isValidPassword)
    }

    fun onPasswordVisibilityChanged() {
        uiState = uiState.copy(showPassword = !uiState.showPassword)
    }

    fun onLogin() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            loginUseCase(email = uiState.email, password = uiState.password)

            uiState = uiState.copy(
                isLoading = false,
                isSuccess = true,
                errorMessageId = null
            )
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val isValidEmail: Boolean = false,
    val password: String = "",
    val isValidPassword: Boolean = false,
    val showPassword: Boolean = false,
    val isLoading: Boolean = false,
    val isSuccess: Boolean? = null,
    val errorMessageId: Int? = null
)