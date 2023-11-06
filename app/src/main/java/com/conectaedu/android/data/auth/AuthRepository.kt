package com.conectaedu.android.data.auth

import com.conectaedu.android.data.auth.local.AuthLocalDataSource
import com.conectaedu.android.data.auth.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) {
    suspend fun getCurrentUserId() = authLocalDataSource.getCurrentUserId().first()

    suspend fun login(email: String, password: String) {
        val userId = authRemoteDataSource.login(email, password)
        authLocalDataSource.saveUserId(userId)
    }

    suspend fun register(email: String, password: String): String {
        val userId = authRemoteDataSource.register(email, password)
        authLocalDataSource.saveUserId(userId)
        return userId
    }
}