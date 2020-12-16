package com.rizalfahrudin.moviecatalogue.core.domain.repository

import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface ImplMovieTvRepository {
    fun getDataMovieTv(typePosition: Int): Flow<Resource<List<MovieTv>>>
    fun getDataDetailMovieTv(typePosition: Int, id: Int): Flow<Resource<MovieTv>>
    fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean)
    fun getDataMovieTvFavorite(typePosition: Int): Flow<List<MovieTv>>
}