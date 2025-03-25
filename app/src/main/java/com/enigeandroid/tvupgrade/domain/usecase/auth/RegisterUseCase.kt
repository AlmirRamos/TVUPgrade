package com.enigeandroid.tvupgrade.domain.usecase.auth

import com.enigeandroid.tvupgrade.domain.repository.Auth.FirebaseAuthentication
import javax.inject.Inject

class RegisterUseCase @Inject constructor (
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.register(email, password)
    }

}