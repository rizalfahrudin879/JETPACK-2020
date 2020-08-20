package com.rizalfahrudin.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.rizalfahrudin.moviecatalogue.data.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.utils.Data

class DetailViewModel : ViewModel() {
    private var id: Int = 0


    fun setIdData(id: Int) {
        this.id = id
    }

    fun getDataMovie(): MovieTvEntity {
        lateinit var movie: MovieTvEntity
        val movieEntities = Data.generateDataMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == id) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getDataTv(): MovieTvEntity {
        lateinit var tv: MovieTvEntity
        val tvEntities = Data.generateDataTv()
        for (tvEntity in tvEntities) {
            if (tvEntity.id == id) {
                tv = tvEntity
            }
        }
        return tv
    }


}