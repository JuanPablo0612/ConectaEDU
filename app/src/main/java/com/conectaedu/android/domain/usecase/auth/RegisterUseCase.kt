package com.conectaedu.android.domain.usecase.auth

import com.conectaedu.android.data.auth.AuthRepository
import com.conectaedu.android.data.users.UsersRepository
import com.conectaedu.android.domain.model.User
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        val userId = authRepository.register(email, password)

        val user = User(
            id = userId,
            email = email,
            firstName = firstName,
            lastName = lastName,
            admin = email.contains("admin")
        )

        usersRepository.save(user)
    }
}