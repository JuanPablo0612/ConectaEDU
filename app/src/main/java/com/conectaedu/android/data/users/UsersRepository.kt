package com.conectaedu.android.data.users

import com.conectaedu.android.data.model.toDomain
import com.conectaedu.android.data.users.remote.UsersRemoteDataSource
import com.conectaedu.android.domain.model.User
import com.conectaedu.android.domain.model.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(private val usersRemoteDataSource: UsersRemoteDataSource) {
    suspend fun save(user: User) {
        val userModel = user.toModel()
        usersRemoteDataSource.save(userModel)
    }

    fun get(id: String) = usersRemoteDataSource.get(id).map { userModel -> userModel!!.toDomain() }

    suspend fun registerArea(user: User, areaId: String) {
        val userModel = user.toModel()
        usersRemoteDataSource.registerArea(userModel, areaId)
    }
}