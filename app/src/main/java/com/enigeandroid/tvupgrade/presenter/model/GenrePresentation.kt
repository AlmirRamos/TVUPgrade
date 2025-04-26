package com.enigeandroid.tvupgrade.presenter.model

import android.os.Parcelable
import com.enigeandroid.tvupgrade.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenrePresentation(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
) : Parcelable
