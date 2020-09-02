package com.rizalfahrudin.moviecatalogue.data.source.remote

import com.google.gson.Gson
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
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(remoteApiRepository: RemoteApiRepository): RemoteDataSource =
            instance?: synchronized(this) {
                instance?: RemoteDataSource(remoteApiRepository, Gson(), CoroutineContextProvider())
            }
    }

    fun getMovieTv(typePosition: Int, callback: LoadCallback) {
        EspressoIdlingResource.increment()
        GlobalScope.launch (context.main){
            val movieTvResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(RemoteDataApi().getMovieTv(typePosition)).await(),
                if (typePosition == 0) MovieResponse::class.java else TvResponse::class.java
            )

            if (typePosition == 0) callback.onAllReceivedMovie(movieTvResponse as MovieResponse)
            else callback.onAllReceivedTv(movieTvResponse as TvResponse)

            EspressoIdlingResource.decrement()
        }
    }

    fun getDetailMovieTv(typePosition: Int, id: Int, callback: LoadDetailCallback) {
        EspressoIdlingResource.increment()
        GlobalScope.launch (context.main) {

            val detailMovieTvResponse = gson.fromJson(
                remoteApiRepository.doRequestAsync(RemoteDataApi().getDetailMovieTv(typePosition, id)).await(),
                if (typePosition == 0) MovieEntityResponse::class.java else TvEntityResponse::class.java
            )

            if (typePosition == 0) callback.onAllReceivedMovie(detailMovieTvResponse as MovieEntityResponse)
            else callback.onAllReceivedTv(detailMovieTvResponse as TvEntityResponse)

            EspressoIdlingResource.decrement()
        }
    }
    interface LoadCallback {
        fun onAllReceivedMovie(movieResponse: MovieResponse)
        fun onAllReceivedTv(tvResponse: TvResponse)
    }
    interface LoadDetailCallback {
        fun onAllReceivedMovie(movieResponse: MovieEntityResponse)
        fun onAllReceivedTv(tvResponse: TvEntityResponse)
    }
}
