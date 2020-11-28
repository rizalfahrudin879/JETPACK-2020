package com.rizalfahrudin.moviecatalogue.core.data.source.remote.api

import com.rizalfahrudin.moviecatalogue.BuildConfig.API_KEY
import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_URL


class RemoteDataApi {
    fun getMovie(): String = BASE_URL + "movie/popular?api_key=" + API_KEY
    fun getTv(): String = BASE_URL + "tv/popular?api_key=" + API_KEY

    fun getMovieById(id: Int): String = BASE_URL + "movie/" + id + "?api_key=" + API_KEY
    fun getTveById(id: Int): String = BASE_URL + "tv/" + id + "?api_key=" + API_KEY
}