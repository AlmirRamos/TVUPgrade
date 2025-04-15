package com.enigeandroid.tvupgrade.domain.usecase.movie

import com.enigeandroid.tvupgrade.data.mapper.toDomain
import com.enigeandroid.tvupgrade.domain.model.Genre
import com.enigeandroid.tvupgrade.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: MovieRepository
){

   /* suspend operator fun invoke(apiKey: String, language: String?): List<Genre> {
        return repository.getGenres(
            apiKey = apiKey,
            language = language
        ).genres?.mapNotNull { it.toDomain() } ?: emptyList()
    }*/

}