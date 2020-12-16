package com.rizalfahrudin.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_IMG_URL
import com.rizalfahrudin.moviecatalogue.core.data.source.MovieTvDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvEntityResponse
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.response.TvResponse
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor
import com.rizalfahrudin.moviecatalogue.core.vo.Resource

class FakeMovieTvRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : MovieTvDataSource {

    override fun getDataMovieTv(typePosition: Int): LiveData<Resource<PagedList<MovieTvEntity>>> {
        return when (typePosition) {
            0 -> {
                object :
                    _root_ide_package_.com.rizalfahrudin.moviecatalogue.core.data.NetworkBoundResource<PagedList<MovieTvEntity>, MovieResponse>(
                        appExecutor
                    ) {
                    override fun loadFromDB(): LiveData<PagedList<MovieTvEntity>> {
                        val config = PagedList.Config.Builder()
                            .setEnablePlaceholders(false)
                            .setInitialLoadSizeHint(4)
                            .setPageSize(4)
                            .build()
                        return LivePagedListBuilder(
                            localDataSource.getMovieTv(typePosition),
                            config
                        ).build()
                    }

                    override fun showFetchData(data: PagedList<MovieTvEntity>?): Boolean {
                        return data == null || data.isEmpty()
                    }

                    override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                        return remoteDataSource.getMovie()
                    }

                    override fun saveCallResult(data: MovieResponse) {
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
                }.asLiveData()
            }
            else -> {
                object :
                    _root_ide_package_.com.rizalfahrudin.moviecatalogue.core.data.NetworkBoundResource<PagedList<MovieTvEntity>, TvResponse>(
                        appExecutor
                    ) {
                    override fun loadFromDB(): LiveData<PagedList<MovieTvEntity>> {
                        val config = PagedList.Config.Builder()
                            .setEnablePlaceholders(false)
                            .setInitialLoadSizeHint(4)
                            .setPageSize(4)
                            .build()
                        return LivePagedListBuilder(
                            localDataSource.getMovieTv(typePosition),
                            config
                        ).build()
                    }

                    override fun showFetchData(data: PagedList<MovieTvEntity>?): Boolean {
                        return data == null || data.isEmpty()
                    }

                    override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                        return remoteDataSource.getTv()
                    }

                    override fun saveCallResult(data: TvResponse) {
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
                }.asLiveData()
            }
        }
    }

    override fun getDataDetailMovieTv(
        typePosition: Int,
        id: Int
    ): LiveData<Resource<MovieTvEntity>> {
        return when(typePosition) {
            0 -> {
                object :
                    _root_ide_package_.com.rizalfahrudin.moviecatalogue.core.data.NetworkBoundResource<MovieTvEntity, MovieEntityResponse>(
                        appExecutor
                    ) {
                    override fun loadFromDB(): LiveData<MovieTvEntity> {
                        return localDataSource.getMovieTvById(id)
                    }

                    override fun showFetchData(data: MovieTvEntity?): Boolean {
                        return data == null
                    }

                    override fun createCall(): LiveData<ApiResponse<MovieEntityResponse>> {
                        return remoteDataSource.getMovieById(id)
                    }

                    override fun saveCallResult(data: MovieEntityResponse) {
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
                }.asLiveData()
            }
            else -> {
                object :
                    _root_ide_package_.com.rizalfahrudin.moviecatalogue.core.data.NetworkBoundResource<MovieTvEntity, TvEntityResponse>(
                        appExecutor
                    ) {
                    override fun loadFromDB(): LiveData<MovieTvEntity> {
                        return localDataSource.getMovieTvById(id)
                    }

                    override fun showFetchData(data: MovieTvEntity?): Boolean {
                        return data == null
                    }

                    override fun createCall(): LiveData<ApiResponse<TvEntityResponse>> {
                        return remoteDataSource.getTvById(id)
                    }

                    override fun saveCallResult(data: TvEntityResponse) {
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
                }.asLiveData()
            }
        }
    }

    override fun setDataMovieTvFavorite(movieTvEntity: MovieTvEntity, state: Boolean) {
        return appExecutor.diskIO().execute {
            localDataSource.setMovieTvFavorite(movieTvEntity, state)
        }
    }

    override fun getDataMovieTvFavorite(typePosition: Int): LiveData<PagedList<MovieTvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovieTv(typePosition), config).build()
    }

}