package com.conectaedu.android.data.studygroups.remote

import com.conectaedu.android.data.model.StudyGroupModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StudyGroupsRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByCourse(courseId: String) =
        firestore.collection("studyGroups").whereEqualTo("courseId", courseId)
            .dataObjects<StudyGroupModel>()

    fun getById(studyGroupId: String) =
        firestore.document("studyGroups/$studyGroupId")
            .dataObjects<StudyGroupModel>()

    suspend fun add(studyGroupModel: StudyGroupModel) {
        val document = firestore.collection("studyGroups").add(studyGroupModel).await()
        val id = document.id
        document.update("id", id).await()
    }
}