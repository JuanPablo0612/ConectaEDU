package com.conectaedu.android.data.areas

import com.conectaedu.android.data.areas.remote.AreasRemoteDataSource
import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.domain.model.Area
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AreasRepository @Inject constructor(private val areasRemoteDataSource: AreasRemoteDataSource) {
    fun getAll() =
        areasRemoteDataSource.getAll().map { it.map { areaModel -> areaModel.toDomain() } }

    fun getById(id: String) = areasRemoteDataSource.getById(id).map { it!!.toDomain() }

    suspend fun add(area: Area) {
        val areaModel = area.toModel()
        areasRemoteDataSource.add(areaModel)
    }
}