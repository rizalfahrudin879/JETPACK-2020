package com.rizalfahrudin.moviecatalogue.core.domain.repository

import androidx.lifecycle.LiveData
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

interface ImplMovieTvRepository {
    fun getDataMovieTv(typePosition: Int): LiveData<Resource<List<MovieTv>>>
    fun getDataDetailMovieTv(typePosition: Int, id: Int): LiveData<Resource<MovieTv>>
    fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean)
    fun getDataMovieTvFavorite(typePosition: Int): LiveData<List<MovieTv>>
}