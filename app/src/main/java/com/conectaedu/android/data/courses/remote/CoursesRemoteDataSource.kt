package com.conectaedu.android.data.courses.remote

import com.conectaedu.android.data.model.CourseModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CoursesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByAreaId(areaId: String) =
        firestore.collection("courses").whereEqualTo("areaId", areaId).dataObjects<CourseModel>()

    fun getById(courseId: String) =
        firestore.document("courses/$courseId").dataObjects<CourseModel>()

    suspend fun add(courseModel: CourseModel) {
        val document = firestore.collection("courses").add(courseModel).await()
        val id = document.id
        document.update("id", id).await()
    }
}