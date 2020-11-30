package com.rizalfahrudin.moviecatalogue.core.di

import com.rizalfahrudin.moviecatalogue.core.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieTvRepository: MovieTvRepository): ImplMovieTvRepository
}