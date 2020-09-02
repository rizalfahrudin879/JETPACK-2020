package com.rizalfahrudin.moviecatalogue.di

import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteApiRepository
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteDataApi
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun providerRepository(): MovieTvRepository {
        val remoteDataSource = RemoteDataSource.getInstance(RemoteApiRepository())
        return MovieTvRepository.getInstance(remoteDataSource)
    }
}