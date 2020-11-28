package com.rizalfahrudin.moviecatalogue.core.di

import android.content.Context
import com.rizalfahrudin.moviecatalogue.core.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.room.MovieTvDatabase
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.api.RemoteApiRepository
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.api.RemoteDataApi
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvInteractor
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor

object Injection {
    private fun providerRepository(context: Context): ImplMovieTvRepository {
        val database = MovieTvDatabase.getInstance(context)
        val appExecutor =
            AppExecutor()
        val remoteDataSource = RemoteDataSource.getInstance(
            RemoteApiRepository(),
            RemoteDataApi()
        )
        val localDataSource = LocalDataSource.getInstance(database.movieTvDao())
        return MovieTvRepository.getInstance(remoteDataSource, localDataSource, appExecutor)
    }

    fun provideMovieTvUseCase(context: Context): MovieTvUseCase {
        val repository = providerRepository(context)
        return MovieTvInteractor(repository)
    }
}