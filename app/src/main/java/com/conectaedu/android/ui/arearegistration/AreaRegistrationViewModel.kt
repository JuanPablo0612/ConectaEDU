package com.conectaedu.android.ui.arearegistration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.areas.AddAreaUseCase
import com.conectaedu.android.domain.usecase.areas.GetAllAreasUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import com.conectaedu.android.domain.usecase.user.RegisterAreaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AreaRegistrationViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getAllAreasUseCase: GetAllAreasUseCase,
    private val registerAreaUseCase: RegisterAreaUseCase,
    private val addAreaUseCase: AddAreaUseCase
) : ViewModel() {
    var uiState by mutableStateOf(AreaRegistrationUiState())
        private set

    init {
        getUnregisteredAreas()
    }

    fun onShowAddAreaDialogChanged() {
        uiState = uiState.copy(showAddAreaDialog = !uiState.showAddAreaDialog)
    }

    fun onAddArea(name: String) {
        viewModelScope.launch {
            addAreaUseCase(name)
            onShowAddAreaDialogChanged()
        }
    }

    fun onRegisterArea(areaId: String) {
        viewModelScope.launch {
            registerAreaUseCase(uiState.currentUser, areaId)
        }
    }

    private fun getUnregisteredAreas() {
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

data class AreaRegistrationUiState(
    val currentUser: User = User(),
    val allAreas: List<Area> = emptyList(),
    val showAddAreaDialog: Boolean = false
)