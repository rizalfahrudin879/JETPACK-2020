package com.rizalfahrudin.moviecatalogue.core.domain.usecase

import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieTvUseCase {
    fun getDataMovieTv(typePosition: Int): Flow<Resource<List<MovieTv>>>
    fun getDataDetailMovieTv(typePosition: Int, id: Int): Flow<Resource<MovieTv>>
    fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean)
    fun getDataMovieTvFavorite(typePosition: Int): Flow<List<MovieTv>>
}