package com.rizalfahrudin.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity

interface MovieTvDataSource {
    fun getDataMovieTv(typePosition: Int): LiveData<List<MovieTvEntity>>
    fun getDataDetailMovieTv(typePosition: Int, id: Int): LiveData<MovieTvEntity>

}