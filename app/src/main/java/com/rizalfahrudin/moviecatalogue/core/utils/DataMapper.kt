package com.rizalfahrudin.moviecatalogue.core.utils

import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import org.w3c.dom.Entity

object DataMapper {
    fun mapEntitiesToDomain(input: List<MovieTvEntity>): List<MovieTv> =
        input.map {
            MovieTv(
                id = it.id,
                title = it.title,
                description = it.description,
                image =  it.image,
                type = it.type,
                favorite = it.favorite,
            )
        }

    fun mapDomainToEntity(input: MovieTv) = MovieTvEntity(
        id = input.id,
        title = input.title,
        description = input.description,
        image =  input.image,
        type = input.type,
        favorite = input.favorite,
    )

    fun mapEntityToDomain(input: MovieTvEntity) = MovieTv(
        id = input.id,
        title = input.title,
        description = input.description,
        image =  input.image,
        type = input.type,
        favorite = input.favorite,
    )
}