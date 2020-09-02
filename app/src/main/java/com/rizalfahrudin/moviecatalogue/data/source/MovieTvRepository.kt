package com.rizalfahrudin.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_IMG_URL
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.data.source.remote.response.TvResponse

class MovieTvRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieTvDataSource{

    companion object {
        @Volatile
        private var instance: MovieTvRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieTvRepository =
            instance?: synchronized(this) {
                instance?: MovieTvRepository(remoteDataSource)
            }
    }
    override fun getDataMovieTv(typePosition: Int): LiveData<List<MovieTvEntity>> {
        val movieTvResults = MutableLiveData<List<MovieTvEntity>>()
        remoteDataSource.getMovieTv(typePosition, object: RemoteDataSource.LoadCallback{

            override fun onAllReceivedMovie(movieResponse: MovieResponse) {
                val movieTvEntities = ArrayList<MovieTvEntity>()
                for (response in movieResponse.movie) {
                        val movieTvEntity = MovieTvEntity(
                            response.id,
                            response.original_title,
                            response.overview,
                            BASE_IMG_URL + response.poster_path
                        )
                        movieTvEntities.add(movieTvEntity)
                    }
                movieTvResults.postValue(movieTvEntities)
            }

            override fun onAllReceivedTv(tvResponse: TvResponse) {
                val movieTvEntities = ArrayList<MovieTvEntity>()

                for (response in tvResponse.tv) {
                        val movieTvEntity = MovieTvEntity(
                            response.id,
                            response.original_name,
                            response.overview,
                            BASE_IMG_URL + response.poster_path
                        )
                        movieTvEntities.add(movieTvEntity)
                    }
                movieTvResults.postValue(movieTvEntities)
            }
        })
        return movieTvResults
    }

    override fun getDataDetailMovieTv(typePosition: Int, id: Int): LiveData<MovieTvEntity> {
        val movieTvResult = MutableLiveData<MovieTvEntity>()
        remoteDataSource.getDetailMovieTv(typePosition, id, object : RemoteDataSource.LoadDetailCallback{
            override fun onAllReceivedMovie(movieResponse: MovieEntityResponse) {
                val movieTvEntity = MovieTvEntity(
                    movieResponse.id,
                    movieResponse.original_title,
                    movieResponse.overview,
                    BASE_IMG_URL + movieResponse.poster_path
                )
                movieTvResult.postValue(movieTvEntity)
            }

            override fun onAllReceivedTv(tvResponse: TvEntityResponse) {
                val movieTvEntity = MovieTvEntity(
                    tvResponse.id,
                    tvResponse.original_name,
                    tvResponse.overview,
                    BASE_IMG_URL + tvResponse.poster_path
                )
                movieTvResult.postValue(movieTvEntity)
            }
        })
        return movieTvResult
    }
}