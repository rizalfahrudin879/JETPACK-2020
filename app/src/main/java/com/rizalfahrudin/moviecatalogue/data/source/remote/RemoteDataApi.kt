package com.rizalfahrudin.moviecatalogue.data.source.remote

import com.rizalfahrudin.moviecatalogue.BuildConfig.API_KEY
import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_URL


class RemoteDataApi {
    fun getMovieTv(typePosition: Int): String {
        return if (typePosition == 0)  BASE_URL + "movie/popular?api_key=" + API_KEY
        else  BASE_URL + "tv/popular?api_key=" + API_KEY
    }

    fun getDetailMovieTv(typePosition: Int, id: Int): String {
        return if (typePosition == 0)  BASE_URL + "movie/"+ id +"?api_key=" + API_KEY
        else  BASE_URL + "tv/"+ id +"?api_key=" + API_KEY
    }
}