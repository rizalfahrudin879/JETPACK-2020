package com.rizalfahrudin.moviecatalogue.di

import android.content.Context
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.data.source.local.room.MovieTvDatabase
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.data.source.remote.api.RemoteApiRepository
import com.rizalfahrudin.moviecatalogue.data.source.remote.api.RemoteDataApi
import com.rizalfahrudin.moviecatalogue.utils.AppExecutor

object Injection {
    fun providerRepository(context: Context): MovieTvRepository {
        val database = MovieTvDatabase.getInstance(context)
        val appExecutor = AppExecutor()
        val remoteDataSource = RemoteDataSource.getInstance(RemoteApiRepository(), RemoteDataApi())
        val localDataSource = LocalDataSource.getInstance(database.movieTvDao())
        return MovieTvRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }
}