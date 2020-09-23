package com.rizalfahrudin.moviecatalogue.utils

import com.google.gson.Gson
import com.rizalfahrudin.moviecatalogue.BuildConfig
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.TvResponse

object DataDummy {


    fun generateDataMovie(): List<MovieTvEntity> {
        val movieResults = ArrayList<MovieTvEntity>()

        val post = Gson().fromJson(JsonStringData().loadMovie(), MovieResponse::class.java)

        for (response in post.movie) {
            val movieTvEntity = MovieTvEntity(
                response.id,
                response.original_title,
                response.overview,
                BuildConfig.BASE_IMG_URL + response.poster_path,
                0
            )
            movieResults.add(movieTvEntity)
        }
        return movieResults
    }


    fun generateDataTv(): List<MovieTvEntity> {
        val tvResults = ArrayList<MovieTvEntity>()
        val post = Gson().fromJson(JsonStringData().loadTv(), TvResponse::class.java)
        for (response in post.tv) {
            val movieTvEntity = MovieTvEntity(
                response.id,
                response.original_name,
                response.overview,
                BuildConfig.BASE_IMG_URL + response.poster_path,
                1
            )
            tvResults.add(movieTvEntity)
        }
        return tvResults
    }

    fun generateRemoteDummyMovie(): MovieResponse {
        return Gson().fromJson(JsonStringData().loadMovie(), MovieResponse::class.java)
    }

    fun generateRemoteDummyTv(): TvResponse {
        return Gson().fromJson(JsonStringData().loadTv(), TvResponse::class.java)
    }

    fun generateDummyMovieTvAndSetFavorite(
        movieTvEntity: MovieTvEntity,
        favorite: Boolean
    ): MovieTvEntity {
        movieTvEntity.favorite = favorite
        return movieTvEntity
    }


}