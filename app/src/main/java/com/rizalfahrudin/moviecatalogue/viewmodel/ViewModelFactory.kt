package com.rizalfahrudin.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.di.Injection
import com.rizalfahrudin.moviecatalogue.ui.detail.DetailViewModel
import com.rizalfahrudin.moviecatalogue.ui.main.content.MovieTvViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(private val mMovieTvRepository: MovieTvRepository): ViewModelProvider.NewInstanceFactory(){
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providerRepository(context))
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(MovieTvViewModel::class.java) -> {
                MovieTvViewModel(mMovieTvRepository) as T

            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(mMovieTvRepository) as T

            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}