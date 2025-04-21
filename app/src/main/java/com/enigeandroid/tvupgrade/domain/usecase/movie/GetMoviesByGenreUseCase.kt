package com.enigeandroid.tvupgrade.domain.usecase.movie

import com.enigeandroid.tvupgrade.data.mapper.toDomain
import com.enigeandroid.tvupgrade.domain.model.Genre
import com.enigeandroid.tvupgrade.domain.model.Movie
import com.enigeandroid.tvupgrade.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
){

    suspend operator fun invoke(apiKey: String, language: String?, genreId: Int?): List<Movie> {
        return repository.getMoviesByGenre(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).map { it.toDomain() }
    }

}