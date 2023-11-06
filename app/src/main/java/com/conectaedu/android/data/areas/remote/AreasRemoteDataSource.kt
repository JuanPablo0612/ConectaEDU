package com.conectaedu.android.data.areas.remote

import com.conectaedu.android.data.model.AreaModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AreasRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAll() = firestore.collection("areas").dataObjects<AreaModel>()

    fun getById(id: String) = firestore.document("areas/$id").dataObjects<AreaModel>()

    suspend fun add(areaModel: AreaModel) {
        val document = firestore.collection("areas").add(areaModel).await()
        val id = document.id
        document.update("id", id).await()
    }
}