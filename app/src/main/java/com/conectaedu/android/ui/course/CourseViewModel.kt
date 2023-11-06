package com.conectaedu.android.ui.course

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conectaedu.android.domain.model.Course
import com.conectaedu.android.domain.model.StudyGroup
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.usecase.courses.GetCourseByIdUseCase
import com.conectaedu.android.domain.usecase.studygroups.AddStudyGroupUseCase
import com.conectaedu.android.domain.usecase.studygroups.GetAllStudyGroupsByCourseUseCase
import com.conectaedu.android.domain.usecase.user.GetCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCourseByIdUseCase: GetCourseByIdUseCase,
    private val getAllStudyGroupsByCourseUseCase: GetAllStudyGroupsByCourseUseCase,
    private val addStudyGroupUseCase: AddStudyGroupUseCase
) : ViewModel() {
    var uiState by mutableStateOf(CourseUiState())
        private set

    fun init(areaId: String, courseId: String) {
        uiState = uiState.copy(areaId = areaId, courseId = courseId)
        getData()
    }

    fun onAddStudyGroup(name: String, description: String) {
        viewModelScope.launch {
            addStudyGroupUseCase(name = name, description = description, courseId = uiState.courseId)
            onShowAddStudyGroupDialogChanged()
        }
    }

    fun onShowAddStudyGroupDialogChanged() {
        uiState = uiState.copy(showAddStudyGroupDialog = !uiState.showAddStudyGroupDialog)
    }

    private fun getData() {
        viewModelScope.launch {
            val currentUserFlow = getCurrentUserUseCase()
            val courseFlow = getCourseByIdUseCase(uiState.courseId)
            val studyGroupsFlow = getAllStudyGroupsByCourseUseCase(uiState.courseId)

            combine(
                currentUserFlow,
                courseFlow,
                studyGroupsFlow
            ) { currentUser, course, studyGroups ->
                uiState.copy(
                    currentUser = currentUser,
                    course = course,
                    studyGroups = studyGroups
                )
            }.collect {
                uiState = it
            }
        }
    }
}

data class CourseUiState(
    val areaId: String = "",
    val courseId: String = "",
    val currentUser: User = User(),
    val course: Course = Course(),
    val studyGroups: List<StudyGroup> = emptyList(),
    val showAddStudyGroupDialog: Boolean = false
)