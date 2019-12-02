package com.example.domain.mapper

import com.example.data.model.GenreModel
import com.example.domain.model.GenreData

class GenreMapper {

    fun mapModelToData(model: GenreModel) = map(model)

    private fun map(model: GenreModel) = GenreData(
            id = model.id,
            name = model.name
    )

}