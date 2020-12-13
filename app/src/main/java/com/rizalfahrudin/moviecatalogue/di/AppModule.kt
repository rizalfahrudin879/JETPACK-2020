package com.rizalfahrudin.moviecatalogue.di

import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvInteractor
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import com.rizalfahrudin.moviecatalogue.detail.DetailViewModel
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MovieTvUseCase>{
        MovieTvInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { MovieTvViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}