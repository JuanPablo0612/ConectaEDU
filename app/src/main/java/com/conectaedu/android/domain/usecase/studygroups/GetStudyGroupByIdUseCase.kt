package com.conectaedu.android.domain.usecase.studygroups

import com.conectaedu.android.data.studygroups.StudyGroupsRepository
import javax.inject.Inject

class GetStudyGroupByIdUseCase @Inject constructor(private val studyGroupsRepository: StudyGroupsRepository) {
    operator fun invoke(studyGroupId: String) =
        studyGroupsRepository.getById(studyGroupId)
}