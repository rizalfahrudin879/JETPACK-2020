package com.rizalfahrudin.moviecatalogue.core.data.source.local

import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.core.data.source.local.room.MovieTvDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource private constructor(private val mMovieTvDao: MovieTvDao){
    companion object {
        private val INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieTvDao: MovieTvDao): LocalDataSource {
            return INSTANCE
                ?: LocalDataSource(
                    mMovieTvDao
                )
        }
    }

    fun getMovieTv(type: Int): Flow<List<MovieTvEntity>> {
        return mMovieTvDao.getMovieTv(type)
    }

    fun getMovieTvById(id: Int): Flow<MovieTvEntity> {
        return mMovieTvDao.getMovieTvById(id)
    }

    fun getFavoriteMovieTv(type: Int): Flow<List<MovieTvEntity>> {
        return mMovieTvDao.getFavoriteMovieTv(type)
    }

    suspend fun insertListMovieTv(movieTvEntities: List<MovieTvEntity>) {
        mMovieTvDao.insertListMovieTv(movieTvEntities)
    }

    suspend fun insertMovieTv(movieTvEntities: MovieTvEntity) {
        mMovieTvDao.insertMovieTv(movieTvEntities)
    }

    fun setMovieTvFavorite(movieTvEntity: MovieTvEntity, newState: Boolean) {
        movieTvEntity.favorite = newState
        mMovieTvDao.updateMovieTv(movieTvEntity)
    }

}