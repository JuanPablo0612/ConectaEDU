package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.Message
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

data class MessageModel(
    val id: String,
    val timestamp: Timestamp,
    val senderId: String,
    val text: String,
    val imageUrl: String,
    val studyGroupId: String
) {
    constructor() : this(
        id = "",
        timestamp = Timestamp.now(),
        senderId = "",
        text = "",
        imageUrl = "",
        studyGroupId = ""
    )
}

fun MessageModel.toDomain() = Message(
    id = id,
    senderId = senderId,
    text = text,
    imageUrl = imageUrl,
    studyGroupId = studyGroupId
)