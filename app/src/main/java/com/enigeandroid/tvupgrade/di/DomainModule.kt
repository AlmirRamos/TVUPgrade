package com.enigeandroid.tvupgrade.di

import com.enigeandroid.tvupgrade.data.repository.auth.FirebaseAuthenticationImpl
import com.enigeandroid.tvupgrade.data.repository.movie.MovieRepositoryImpl
import com.enigeandroid.tvupgrade.domain.repository.auth.FirebaseAuthentication
import com.enigeandroid.tvupgrade.domain.repository.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl
    ): FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

}