package com.rizalfahrudin.moviecatalogue.core.di

import android.content.Context
import androidx.room.Room
import com.rizalfahrudin.moviecatalogue.core.data.source.local.room.MovieTvDao
import com.rizalfahrudin.moviecatalogue.core.data.source.local.room.MovieTvDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieTvDatabase {
        return Room.databaseBuilder(
            context,
            MovieTvDatabase::class.java, "movie_tv.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieTvDao(database: MovieTvDatabase):MovieTvDao {
        return database.movieTvDao()
    }
}