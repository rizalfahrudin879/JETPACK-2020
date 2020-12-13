//package com.rizalfahrudin.moviecatalogue.core.ui
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.rizalfahrudin.moviecatalogue.core.domain.usecase.MovieTvUseCase
//import com.rizalfahrudin.moviecatalogue.detail.DetailViewModel
//import com.rizalfahrudin.moviecatalogue.main.content.MovieTvViewModel
//
//@Suppress("UNCHECKED_CAST")
//class ViewModelFactory private constructor(private val movieTvUseCase: MovieTvUseCase) :
//    ViewModelProvider.NewInstanceFactory() {
//    companion object {
//        @Volatile
//        private var instance: ViewModelFactory? = null
//
//        fun getInstance(context: Context): ViewModelFactory =
//            instance
//                ?: synchronized(this) {
//                    instance
//                        ?: ViewModelFactory(
//                            Injection.provideMovieTvUseCase(context)
//                        )
//                }
//    }
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//
//        return when {
//            modelClass.isAssignableFrom(MovieTvViewModel::class.java) -> {
//                MovieTvViewModel(
//                    movieTvUseCase
//                ) as T
//
//            }
//            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
//                DetailViewModel(
//                    movieTvUseCase
//                ) as T
//
//            }
//            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//}