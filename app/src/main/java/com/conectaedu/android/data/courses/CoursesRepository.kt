package com.conectaedu.android.data.courses

import com.conectaedu.android.data.courses.remote.CoursesRemoteDataSource
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Course
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CoursesRepository @Inject constructor(private val coursesRemoteDataSource: CoursesRemoteDataSource) {
    fun getAllByAreaId(areaId: String) = coursesRemoteDataSource.getAllByAreaId(areaId)
        .map { it.map { courseModel -> courseModel.toDomain() } }

    fun getById(courseId: String) =
        coursesRemoteDataSource.getById(courseId).map { it!!.toDomain() }

    suspend fun add(course: Course) {
        val courseModel = course.toModel()
        coursesRemoteDataSource.add(courseModel)
    }
}