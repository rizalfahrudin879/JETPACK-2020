package com.rizalfahrudin.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity

@Database(entities = [MovieTvEntity::class],
version = 1, exportSchema = false)
abstract class MovieTvDatabase: RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao
}