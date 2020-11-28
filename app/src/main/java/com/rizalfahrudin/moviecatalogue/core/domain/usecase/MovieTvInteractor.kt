package com.rizalfahrudin.moviecatalogue.core.domain.usecase

import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository

class MovieTvInteractor(private val implMovieTvRepository: ImplMovieTvRepository): MovieTvUseCase{
    override fun getDataMovieTv(typePosition: Int) =
        implMovieTvRepository.getDataMovieTv(typePosition)

    override fun getDataDetailMovieTv(typePosition: Int, id: Int) =
        implMovieTvRepository.getDataDetailMovieTv(typePosition, id)

    override fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean) =
        implMovieTvRepository.setDataMovieTvFavorite(movieTv, state)

    override fun getDataMovieTvFavorite(typePosition: Int) =
        implMovieTvRepository.getDataMovieTvFavorite(typePosition)

}