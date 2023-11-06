package com.conectaedu.android.data.users.remote

import com.conectaedu.android.data.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    suspend fun save(userModel: UserModel) {
        firestore.document("users/${userModel.id}").set(userModel).await()
    }

    fun get(id: String) = firestore.document("users/$id").dataObjects<UserModel>()

    suspend fun registerArea(userModel: UserModel, areaId: String) {
        val areas = userModel.registeredAreaIds.toMutableList()
        areas.add(areaId)
        firestore.document("users/${userModel.id}")
            .update("registeredAreaIds", areas.toList())
            .await()
    }
}