package com.rizalfahrudin.moviecatalogue.favorite.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase

class FavoriteViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {
    var typePosition: Int = 0
    fun setTypeMovieTv(typePosition: Int?) {
        if (typePosition != null) {
            this.typePosition = typePosition
        }
    }

    fun getDataMovieTvFavorite(): LiveData<List<MovieTv>> {
        return movieTvUseCase.getDataMovieTvFavorite(typePosition).asLiveData()
    }
}