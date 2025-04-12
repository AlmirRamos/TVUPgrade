package com.enigeandroid.tvupgrade.domain.usecase.auth

import com.enigeandroid.tvupgrade.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.login(email, password)
    }

}