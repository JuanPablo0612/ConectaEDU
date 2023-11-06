package com.conectaedu.android.domain.usecase.courses

import com.conectaedu.android.data.courses.CoursesRepository
import javax.inject.Inject

class GetCourseByIdUseCase @Inject constructor(private val coursesRepository: CoursesRepository) {
    operator fun invoke(courseId: String) =
        coursesRepository.getById(courseId)
}