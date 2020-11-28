package com.rizalfahrudin.moviecatalogue.core.data.source.local.room

import androidx.room.*
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvDao {
    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE type = :type")
    fun getMovieTv(type: Int): Flow<List<MovieTvEntity>>

    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE type = :type AND favorite = 1")
    fun getFavoriteMovieTv(type: Int): Flow<List<MovieTvEntity>>

    @Transaction
    @Query("SELECT * FROM movie_tv_entities WHERE id = :id")
    fun getMovieTvById(id: Int): Flow<MovieTvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieTv(MovieTvEntities: List<MovieTvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTv(MovieTvEntities: MovieTvEntity)

    @Update
    fun updateMovieTv(movieTvEntity: MovieTvEntity)
}