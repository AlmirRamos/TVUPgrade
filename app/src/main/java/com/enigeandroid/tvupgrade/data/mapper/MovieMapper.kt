package com.enigeandroid.tvupgrade.data.mapper

import com.enigeandroid.tvupgrade.data.model.GenreResponse
import com.enigeandroid.tvupgrade.domain.model.Genre

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}
