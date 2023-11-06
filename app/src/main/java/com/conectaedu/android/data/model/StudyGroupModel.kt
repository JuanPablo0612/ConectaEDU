package com.conectaedu.android.data.model

import com.conectaedu.android.domain.model.StudyGroup

data class StudyGroupModel(
    val id: String,
    val name: String,
    val description: String,
    val courseId: String
) {
    constructor() : this(id = "", name = "", description = "", courseId = "")
}

fun StudyGroupModel.toDomain() = StudyGroup(
    id = id,
    name = name,
    description = description,
    courseId = courseId
)