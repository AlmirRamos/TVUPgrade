package com.enigeandroid.tvupgrade.data.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(

    @SerializedName("genres")
    val genres: List<GenreResponse>?

)
