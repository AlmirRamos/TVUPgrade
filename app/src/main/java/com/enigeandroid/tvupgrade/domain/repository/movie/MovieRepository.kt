package com.enigeandroid.tvupgrade.domain.repository.movie

import com.enigeandroid.tvupgrade.data.model.GenresResponse
import com.enigeandroid.tvupgrade.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(apiKey: String, language: String?): GenresResponse

    suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse>

}