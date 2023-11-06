package com.conectaedu.android.domain.usecase.studygroups

import com.conectaedu.android.data.studygroups.StudyGroupsRepository
import com.conectaedu.android.domain.model.StudyGroup
import javax.inject.Inject

class AddStudyGroupUseCase @Inject constructor(private val studyGroupsRepository: StudyGroupsRepository) {
    suspend operator fun invoke(
        courseId: String,
        name: String,
        description: String
    ) {
        val studyGroup = StudyGroup(name = name, description = description, courseId = courseId)
        studyGroupsRepository.add(studyGroup)
    }
}