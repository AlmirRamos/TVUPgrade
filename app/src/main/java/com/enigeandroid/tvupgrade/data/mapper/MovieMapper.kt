package com.enigeandroid.tvupgrade.data.mapper

import com.enigeandroid.tvupgrade.data.model.GenreResponse
import com.enigeandroid.tvupgrade.data.model.MovieResponse
import com.enigeandroid.tvupgrade.domain.model.Genre
import com.enigeandroid.tvupgrade.domain.model.Movie
import com.enigeandroid.tvupgrade.presenter.model.GenrePresentation

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )

}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}
