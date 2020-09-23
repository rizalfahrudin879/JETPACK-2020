package com.rizalfahrudin.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.vo.Resource

class DetailViewModel(private val movieTvRepository: MovieTvRepository) : ViewModel() {
    val position = MutableLiveData<Int>()
    val id = MutableLiveData<Int>()

    fun setTypeMovieTv(position: Int, id: Int) {
        this.position.value = position
        this.id.value = id
    }

    var movieTvEntity: LiveData<Resource<MovieTvEntity>> =
        Transformations.switchMap(position) { mPosition ->
            Transformations.switchMap(id) { mId ->
                movieTvRepository.getDataDetailMovieTv(mPosition, mId)
            }
        }

    fun setBookmark() {
        val movieTvSource = movieTvEntity.value
        if (movieTvSource != null) {
            val movieTvEntityData = movieTvSource.data
            if (movieTvEntityData != null) {
                val newState = !movieTvEntityData.favorite
                movieTvRepository.setDataMovieTvFavorite(movieTvEntityData, newState)
            }
        }
    }
}