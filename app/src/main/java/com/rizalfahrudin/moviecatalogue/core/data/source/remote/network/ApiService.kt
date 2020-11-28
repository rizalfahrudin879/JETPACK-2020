package com.rizalfahrudin.moviecatalogue.core.data.source.remote.network

import com.rizalfahrudin.moviecatalogue.BuildConfig.API_KEY
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getMovie(): MovieResponse

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getTv(): TvResponse

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovieById(@Path("id") id: Int): MovieEntityResponse

    @GET("tv/{id}?api_key=$API_KEY")
    suspend fun getTvById(@Path("id") id: Int): TvEntityResponse
}