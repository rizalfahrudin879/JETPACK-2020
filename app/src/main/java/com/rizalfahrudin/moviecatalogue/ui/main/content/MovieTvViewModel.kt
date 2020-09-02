package com.rizalfahrudin.moviecatalogue.ui.main.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity

class MovieTvViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    private var typePosition: Int = 0
    fun setTypeMovieTv(typePosition: Int) {
        this.typePosition = typePosition
    }

    fun getDataMovieTv(): LiveData<List<MovieTvEntity>> =
        movieTvRepository.getDataMovieTv(typePosition)
}