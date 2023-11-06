package com.conectaedu.android.domain.usecase.messages

import com.conectaedu.android.data.messages.MessagesRepository
import javax.inject.Inject

class GetAllMessagesByStudyGroupUseCase @Inject constructor(private val messagesRepository: MessagesRepository) {
    operator fun invoke(studyGroupId: String) =
        messagesRepository.getAllByStudyGroup(studyGroupId)
}