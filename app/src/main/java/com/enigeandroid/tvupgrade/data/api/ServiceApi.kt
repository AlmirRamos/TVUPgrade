package com.enigeandroid.tvupgrade.data.api

import com.enigeandroid.tvupgrade.data.model.BasePaginationRemote
import com.enigeandroid.tvupgrade.data.model.GenresResponse
import com.enigeandroid.tvupgrade.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("with_genres") genreId: Int
    ): BasePaginationRemote<List<MovieResponse>>

}