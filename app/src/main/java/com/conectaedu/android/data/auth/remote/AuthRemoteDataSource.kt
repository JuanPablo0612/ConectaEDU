package com.conectaedu.android.data.auth.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val auth: FirebaseAuth) {
    suspend fun login(email: String, password: String): String {
        val authResult = auth.signInWithEmailAndPassword(email, password).await()
        return authResult.user!!.uid
    }

    suspend fun register(email: String, password: String): String {
        val authResult = auth.createUserWithEmailAndPassword(email, password).await()
        return authResult.user!!.uid
    }
}