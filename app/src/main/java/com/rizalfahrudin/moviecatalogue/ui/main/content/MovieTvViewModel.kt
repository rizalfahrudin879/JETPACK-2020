package com.rizalfahrudin.moviecatalogue.ui.main.content

import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.data.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.utils.Data

class MovieTvViewModel : ViewModel() {
    fun getDataMovie(): List<MovieTvEntity> = Data.generateDataMovie()

    fun getDataTv(): List<MovieTvEntity> = Data.generateDataTv()
}