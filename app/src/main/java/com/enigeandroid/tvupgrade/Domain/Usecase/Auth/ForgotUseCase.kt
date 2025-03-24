package com.enigeandroid.tvupgrade.Domain.Usecase.Auth

import com.enigeandroid.tvupgrade.Domain.Repository.Auth.FirebaseAuthentication

class ForgotUseCase constructor (
    private val firebaseAuthentication : FirebaseAuthentication
) {

    suspend operator fun invoke(email: String) {
        firebaseAuthentication.forgot(email)
    }

}