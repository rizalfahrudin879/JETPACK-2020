package com.rizalfahrudin.moviecatalogue.core.data.source

import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_IMG_URL
import com.rizalfahrudin.moviecatalogue.core.data.NetworkBoundResource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvResponse
import com.rizalfahrudin.moviecatalogue.core.domain.model.MovieTv
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor
import com.rizalfahrudin.moviecatalogue.core.utils.DataMapper
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieTvRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : ImplMovieTvRepository {

//    companion object {
//        @Volatile
//        private var instance: MovieTvRepository? = null
//
//        fun getInstance(
//            remoteDataSource: RemoteDataSource,
//            localDataSource: LocalDataSource,
//            appExecutor: AppExecutor
//        ): MovieTvRepository =
//            instance
//                ?: synchronized(this) {
//                    instance
//                        ?: MovieTvRepository(
//                            remoteDataSource,
//                            localDataSource,
//                            appExecutor
//                        )
//                }
//    }

    override fun getDataMovieTv(typePosition: Int): Flow<Resource<List<MovieTv>>> {
        return when (typePosition) {
            0 -> {
                object :
                    NetworkBoundResource<List<MovieTv>, MovieResponse>(appExecutor) {
                    override fun loadFromDB(): Flow<List<MovieTv>> {
                        return localDataSource.getMovieTv(0)
                            .map { DataMapper.mapEntitiesToDomain(it) }
                    }

                    override fun showFetchData(data: List<MovieTv>?): Boolean {
                        return data == null || data.isEmpty()
                    }

                    override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> {
                        return remoteDataSource.getMovie()
                    }

                    override suspend fun saveCallResult(data: MovieResponse) {
                        val movieList = ArrayList<MovieTvEntity>()
                        for (response in data.movie) {
                            val movie =
                                MovieTvEntity(
                                    response.id,
                                    response.original_title,
                                    response.overview,
                                    BASE_IMG_URL + response.poster_path,
                                    typePosition
                                )
                            movieList.add(movie)
                        }
                        localDataSource.insertListMovieTv(movieList)
                    }
                }.asFlow()
            }
            else -> {
                object : NetworkBoundResource<List<MovieTv>, TvResponse>(appExecutor) {
                    override fun loadFromDB(): Flow<List<MovieTv>> {
                        return localDataSource.getMovieTv(1)
                            .map { DataMapper.mapEntitiesToDomain(it) }
                    }

                    override fun showFetchData(data: List<MovieTv>?): Boolean {
                        return data == null || data.isEmpty()
                    }

                    override suspend fun createCall(): Flow<ApiResponse<TvResponse>> {
                        return remoteDataSource.getTv()
                    }

                    override suspend fun saveCallResult(data: TvResponse) {
                        val movieList = ArrayList<MovieTvEntity>()
                        for (response in data.tv) {
                            val movie =
                                MovieTvEntity(
                                    response.id,
                                    response.original_name,
                                    response.overview,
                                    BASE_IMG_URL + response.poster_path,
                                    typePosition
                                )
                            movieList.add(movie)
                        }
                        localDataSource.insertListMovieTv(movieList)
                    }
                }.asFlow()
            }
        }
    }

    override fun getDataDetailMovieTv(
        typePosition: Int,
        id: Int
    ): Flow<Resource<MovieTv>> {
        return when (typePosition) {
            0 -> {
                object : NetworkBoundResource<MovieTv, MovieEntityResponse>(appExecutor) {
                    override fun loadFromDB(): Flow<MovieTv> {
                        return localDataSource.getMovieTvById(id).map {
                            DataMapper.mapEntityToDomain(it)
                        }
                    }

                    override fun showFetchData(data: MovieTv?): Boolean {
                        return data == null
                    }

                    override suspend fun createCall(): Flow<ApiResponse<MovieEntityResponse>> {
                        return remoteDataSource.getMovieById(id)
                    }

                    override suspend fun saveCallResult(data: MovieEntityResponse) {
                        val movie =
                            MovieTvEntity(
                                data.id,
                                data.original_title,
                                data.overview,
                                BASE_IMG_URL + data.poster_path,
                                typePosition,
                                favorite = false
                            )
                        localDataSource.insertMovieTv(movie)
                    }
                }.asFlow()
            }
            else -> {
                object : NetworkBoundResource<MovieTv, TvEntityResponse>(appExecutor) {
                    override fun loadFromDB(): Flow<MovieTv> {
                        return localDataSource.getMovieTvById(id).map {
                            DataMapper.mapEntityToDomain(it)
                        }
                    }

                    override fun showFetchData(data: MovieTv?): Boolean {
                        return data == null
                    }

                    override suspend fun createCall(): Flow<ApiResponse<TvEntityResponse>> {
                        return remoteDataSource.getTvById(id)
                    }

                    override suspend fun saveCallResult(data: TvEntityResponse) {
                        val movie =
                            MovieTvEntity(
                                data.id,
                                data.original_name,
                                data.overview,
                                BASE_IMG_URL + data.poster_path,
                                typePosition,
                                favorite = false
                            )
                        localDataSource.insertMovieTv(movie)
                    }
                }.asFlow()
            }
        }
    }

    override fun setDataMovieTvFavorite(movieTv: MovieTv, state: Boolean) {
        val movieTvEntity = DataMapper.mapDomainToEntity(movieTv)
        return appExecutor.diskIO().execute {
            localDataSource.setMovieTvFavorite(movieTvEntity, state)
        }
    }

    override fun getDataMovieTvFavorite(typePosition: Int): Flow<List<MovieTv>> {
        return localDataSource.getFavoriteMovieTv(typePosition).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

}