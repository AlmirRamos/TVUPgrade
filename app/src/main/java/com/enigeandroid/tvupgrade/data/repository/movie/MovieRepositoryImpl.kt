package com.enigeandroid.tvupgrade.data.repository.movie

import com.enigeandroid.tvupgrade.data.api.ServiceApi
import com.enigeandroid.tvupgrade.data.model.GenresResponse
import com.enigeandroid.tvupgrade.data.model.MovieResponse
import com.enigeandroid.tvupgrade.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : MovieRepository {

    override suspend fun getGenres(apiKey: String, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey = apiKey,
            language = language
        )
    }

    override suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse> {
        return serviceApi.getMoviesByGenre(
            apiKey =  apiKey,
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }
}