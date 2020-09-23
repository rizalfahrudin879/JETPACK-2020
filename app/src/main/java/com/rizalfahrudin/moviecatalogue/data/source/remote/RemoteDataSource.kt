package com.rizalfahrudin.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.rizalfahrudin.moviecatalogue.data.source.remote.api.RemoteApiRepository
import com.rizalfahrudin.moviecatalogue.data.source.remote.api.RemoteDataApi
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.TvResponse
import com.rizalfahrudin.moviecatalogue.utils.CoroutineContextProvider
import com.rizalfahrudin.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RemoteDataSource private constructor(
    private val remoteApiRepository: RemoteApiRepository,
    private val remoteDataApi: RemoteDataApi,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(
            remoteApiRepository: RemoteApiRepository,
            remoteDataApi: RemoteDataApi
        ): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(
                    remoteApiRepository,
                    remoteDataApi,
                    Gson(),
                    CoroutineContextProvider()
                )
            }
    }

    fun getMovie(): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<MovieResponse>>()
        GlobalScope.launch(context.main) {
            val movieResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(
                    remoteDataApi.getMovie()
                ),
                MovieResponse::class.java
            )
            resultMovies.value = ApiResponse.success(movieResponse)
            EspressoIdlingResource.decrement()
        }
        return resultMovies
    }

    fun getTv(): LiveData<ApiResponse<TvResponse>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvResponse>>()
        GlobalScope.launch(context.main) {
            val tvResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(
                    remoteDataApi.getTv()
                ),
                TvResponse::class.java
            )
            resultTv.value = ApiResponse.success(tvResponse)
            EspressoIdlingResource.decrement()
        }
        return resultTv
    }

    fun getMovieById(id: Int): LiveData<ApiResponse<MovieEntityResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieEntityResponse>>()
        GlobalScope.launch(context.main) {
            val movieResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(
                    remoteDataApi.getMovieById(id)
                ),
                MovieEntityResponse::class.java
            )
            resultMovie.value = ApiResponse.success(movieResponse)
            EspressoIdlingResource.decrement()
        }
        return resultMovie
    }

    fun getTvById(id: Int): LiveData<ApiResponse<TvEntityResponse>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvEntityResponse>>()
        GlobalScope.launch(context.main) {
            val tvResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(
                    remoteDataApi.getTveById(id)
                ),
                TvEntityResponse::class.java
            )
            resultTv.value = ApiResponse.success(tvResponse)
            EspressoIdlingResource.decrement()
        }
        return resultTv
    }
}
