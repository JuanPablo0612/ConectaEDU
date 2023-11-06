package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.MessageModel
import com.google.firebase.Timestamp

data class Message(
    val id: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val senderId: String = "",
    val text: String = "",
    val imageUrl: String = "",
    val studyGroupId: String = ""
)

fun Message.toModel() = MessageModel(
    id = id,
    timestamp = timestamp,
    senderId = senderId,
    text = text,
    imageUrl = imageUrl,
    studyGroupId = studyGroupId
)