package com.rizalfahrudin.moviecatalogue.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity

@Dao
interface MovieTvDao {
    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE type = :type")
    fun getMovieTv(type: Int): LiveData<List<MovieTvEntity>>

    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE type = :type AND favorite = 1")
    fun getFavoriteMovieTv(type: Int): LiveData<List<MovieTvEntity>>

    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE id = :id")
    fun getMovieTvById(id: Int): LiveData<MovieTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListMovieTv(MovieTvEntities: List<MovieTvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieTv(MovieTvEntities: MovieTvEntity)

    @Update
    fun updateMovieTv(movieTvEntity: MovieTvEntity)
}