package com.rizalfahrudin.moviecatalogue.main.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

class MovieTvViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {
    var typePosition: Int = 0
    fun setTypeMovieTv(typePosition: Int?) {
        if (typePosition != null) {
            this.typePosition = typePosition
        }
    }

    fun getDataMovieTv(): LiveData<Resource<List<MovieTv>>> {
        return movieTvUseCase.getDataMovieTv(typePosition).asLiveData()
    }

    fun getDataMovieTvFavorite(): LiveData<List<MovieTv>> {
        return movieTvUseCase.getDataMovieTvFavorite(typePosition).asLiveData()
    }
}