package com.enigeandroid.tvupgrade.Domain.Repository.Auth

interface FirebaseAuthentication {

    suspend fun login(email: String, password: String)

    suspend fun register(email: String, password: String)

    suspend fun forgot(email: String)

}