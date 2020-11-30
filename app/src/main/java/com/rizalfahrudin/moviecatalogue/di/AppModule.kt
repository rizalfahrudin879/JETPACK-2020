package com.rizalfahrudin.moviecatalogue.di

import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvInteractor
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideMovieTvUseCase(movieTvInteractor: MovieTvInteractor): MovieTvUseCase
}