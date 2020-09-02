package com.rizalfahrudin.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity

class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    private var typePosition: Int = 0
    private var id: Int = 0
    fun setTypeMovieTv(typePosition: Int, id: Int) {
        this.typePosition = typePosition
        this.id = id
    }

    fun getDataMovie(): LiveData<MovieTvEntity> =
        movieTvRepository.getDataDetailMovieTv(typePosition, id)
}