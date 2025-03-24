package com.enigeandroid.tvupgrade.Domain.Usecase.Auth

import com.enigeandroid.tvupgrade.Domain.Repository.Auth.FirebaseAuthentication

class RegisterUseCase constructor (
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.register(email, password)
    }

}