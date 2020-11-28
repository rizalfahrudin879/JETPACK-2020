package com.rizalfahrudin.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

class MovieTvInteractor(private val implMovieTvRepository: ImplMovieTvRepository): MovieTvUseCase{
    override fun getDataMovieTv(typePosition: Int): LiveData<Resource<List<MovieTv>>> {
        return implMovieTvRepository.getDataMovieTv(typePosition)
    }

    override fun getDataDetailMovieTv(typePosition: Int, id: Int): LiveData<Resource<MovieTv>> {
        return implMovieTvRepository.getDataDetailMovieTv(typePosition, id)
    }

    override fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean) {
        return implMovieTvRepository.setDataMovieTvFavorite(movieTv, state)
    }

    override fun getDataMovieTvFavorite(typePosition: Int): LiveData<List<MovieTv>> {
        return implMovieTvRepository.getDataMovieTvFavorite(typePosition)
    }

}