package com.conectaedu.android.domain.model

import com.conectaedu.android.data.model.CourseModel

data class Course(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val areaId: String = ""
)

fun Course.toModel() = CourseModel(
    id = id,
    name = name,
    description = description,
    areaId = areaId
)