package com.conectaedu.android.data.messages.remote

import com.conectaedu.android.data.model.MessageModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MessagesRemoteDataSource @Inject constructor(private val firestore: FirebaseFirestore) {
    fun getAllByStudyGroup(studyGroupId: String) =
        firestore.collection("messages")
            .whereEqualTo("studyGroupId", studyGroupId)
            .orderBy("timestamp")
            .dataObjects<MessageModel>()

    suspend fun add(messageModel: MessageModel) {
        val document =
            firestore.collection("messages").add(messageModel).await()
        val id = document.id
        document.update("id", id, "timestamp", FieldValue.serverTimestamp()).await()
    }
}