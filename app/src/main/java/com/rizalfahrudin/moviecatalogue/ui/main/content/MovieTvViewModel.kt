package com.rizalfahrudin.moviecatalogue.ui.main.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.vo.Resource

class MovieTvViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    private var typePosition: Int = 0
    fun setTypeMovieTv(typePosition: Int?) {
        if (typePosition != null) {
            this.typePosition = typePosition
        }
    }

    fun getDataMovieTv(): LiveData<Resource<PagedList<MovieTvEntity>>> {
        return movieTvRepository.getDataMovieTv(typePosition)
    }

    fun getDataMovieTvFavorite(): LiveData<PagedList<MovieTvEntity>> {
        return movieTvRepository.getDataMovieTvFavorite(typePosition)
    }
}