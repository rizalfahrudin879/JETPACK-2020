package com.rizalfahrudin.moviecatalogue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

class DetailViewModel(private val movieTvUseCase: MovieTvUseCase) : ViewModel() {
    private val position = MutableLiveData<Int>()
    val id = MutableLiveData<Int>()

    fun setTypeMovieTv(position: Int, id: Int) {
        this.position.value = position
        this.id.value = id
    }

    var movieTv: LiveData<Resource<MovieTv>> =
        Transformations.switchMap(position) { mPosition ->
            Transformations.switchMap(id) { mId ->
                movieTvUseCase.getDataDetailMovieTv(mPosition, mId)
            }
        }

    fun setBookmark() {
        val movieTvSource = movieTv.value
        if (movieTvSource != null) {
            val movieTvData = movieTvSource.data
            if (movieTvData != null) {
                val newState = !movieTvData.favorite
                movieTvUseCase.setDataMovieTvFavorite(movieTvData, newState)
            }
        }
    }
}