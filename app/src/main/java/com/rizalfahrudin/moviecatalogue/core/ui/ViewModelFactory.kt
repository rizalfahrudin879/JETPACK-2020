package com.rizalfahrudin.moviecatalogue.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
import com.rizalfahrudin.moviecatalogue.detail.DetailViewModel
import com.rizalfahrudin.moviecatalogue.di.AppScope
import com.rizalfahrudin.moviecatalogue.main.content.MovieTvViewModel
import javax.inject.Inject


@AppScope
class ViewModelFactory @Inject constructor(private val movieTvUseCase: MovieTvUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MovieTvViewModel::class.java) -> {
                MovieTvViewModel(
                    movieTvUseCase
                ) as T

            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(
                    movieTvUseCase
                ) as T

            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}