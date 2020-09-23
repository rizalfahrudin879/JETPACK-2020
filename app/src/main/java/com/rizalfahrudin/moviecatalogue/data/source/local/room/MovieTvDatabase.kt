package com.rizalfahrudin.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity

@Database(entities = [MovieTvEntity::class],
version = 1, exportSchema = false)
abstract class MovieTvDatabase: RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao

   companion object {
       @Volatile
       private var INSTANCE: MovieTvDatabase? = null

       fun getInstance(context: Context): MovieTvDatabase {
           return INSTANCE?: synchronized(this) {
               INSTANCE?: Room.databaseBuilder(context.applicationContext,
                   MovieTvDatabase::class.java,
                   "movie_tv.db").build()
           }
       }
   }
    
}