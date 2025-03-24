package com.enigeandroid.tvupgrade.Domain.Usecase.Auth

import com.enigeandroid.tvupgrade.Domain.Repository.Auth.FirebaseAuthentication

class LoginUseCase constructor (
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.login(email, password)
    }

}