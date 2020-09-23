package com.rizalfahrudin.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.vo.Resource

interface MovieTvDataSource {
    fun getDataMovieTv(typePosition: Int): LiveData<Resource<PagedList<MovieTvEntity>>>
    fun getDataDetailMovieTv(typePosition: Int, id: Int): LiveData<Resource<MovieTvEntity>>
    fun setDataMovieTvFavorite(movieTvEntity: MovieTvEntity, state: Boolean)
    fun getDataMovieTvFavorite(typePosition: Int): LiveData<PagedList<MovieTvEntity>>
}