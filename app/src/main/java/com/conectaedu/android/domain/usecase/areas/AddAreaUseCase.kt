package com.conectaedu.android.domain.usecase.areas

import com.conectaedu.android.data.areas.AreasRepository
import com.conectaedu.android.domain.model.Area
import javax.inject.Inject

class AddAreaUseCase @Inject constructor(private val areasRepository: AreasRepository) {
    suspend operator fun invoke(name: String) {
        val area = Area(name = name)
        areasRepository.add(area)
    }
}