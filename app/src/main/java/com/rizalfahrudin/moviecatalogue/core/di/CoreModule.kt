package com.rizalfahrudin.moviecatalogue.core.di

import androidx.room.Room
import com.rizalfahrudin.moviecatalogue.BuildConfig.BASE_URL
import com.rizalfahrudin.moviecatalogue.core.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.local.room.MovieTvDatabase
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.core.data.source.remote.network.ApiService
import com.rizalfahrudin.moviecatalogue.core.domain.repository.ImplMovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<MovieTvDatabase>().movieTvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieTvDatabase::class.java, "movie_tv.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<ImplMovieTvRepository> {
        MovieTvRepository(get(),get(),get())
    }
}