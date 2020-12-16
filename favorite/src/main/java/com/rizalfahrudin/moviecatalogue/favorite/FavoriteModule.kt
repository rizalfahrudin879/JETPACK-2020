package com.rizalfahrudin.moviecatalogue.favorite

import com.rizalfahrudin.moviecatalogue.favorite.content.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}