package com.conectaedu.android.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.areas.GetAllAreasUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAllAreasUseCase: GetAllAreasUseCase
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            val currentUserFlow = getCurrentUserUseCase()
            val areasFlow = getAllAreasUseCase()

            combine(currentUserFlow, areasFlow) { currentUser, areas ->
                uiState.copy(
                    currentUser = currentUser,
                    allAreas = areas
                )
            }.collect {
                uiState = it
            }
        }
    }
}

data class HomeUiState(
    val currentUser: User = User(),
    val allAreas: List<Area> = emptyList()
)