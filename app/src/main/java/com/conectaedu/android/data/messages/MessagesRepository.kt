package com.conectaedu.android.data.messages

import com.conectaedu.android.data.messages.remote.MessagesRemoteDataSource
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Message
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessagesRepository @Inject constructor(private val messagesRemoteDataSource: MessagesRemoteDataSource) {
    fun getAllByStudyGroup(
        studyGroupId: String
    ) = messagesRemoteDataSource.getAllByStudyGroup(studyGroupId)
        .map { it.map { messageModel -> messageModel.toDomain() } }

    suspend fun add(message: Message) {
        val messageModel = message.toModel()
        messagesRemoteDataSource.add(messageModel)
    }
}