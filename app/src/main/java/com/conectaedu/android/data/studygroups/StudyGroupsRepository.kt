package com.conectaedu.android.data.studygroups

import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.data.studygroups.remote.StudyGroupsRemoteDataSource
import com.conectaedu.android.domain.model.StudyGroup
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudyGroupsRepository @Inject constructor(private val studyGroupsRemoteDataSource: StudyGroupsRemoteDataSource) {
    fun getAllByCourse(courseId: String) =
        studyGroupsRemoteDataSource.getAllByCourse(courseId)
            .map { it.map { studyGroupModel -> studyGroupModel.toDomain() } }

    fun getById(studyGroupId: String) =
        studyGroupsRemoteDataSource.getById(studyGroupId).map { it!!.toDomain() }

    suspend fun add(studyGroup: StudyGroup) {
        val studyGroupModel = studyGroup.toModel()
        studyGroupsRemoteDataSource.add(studyGroupModel)
    }
}