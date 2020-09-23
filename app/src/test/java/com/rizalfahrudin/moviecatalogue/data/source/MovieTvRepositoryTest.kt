package com.rizalfahrudin.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.rizalfahrudin.moviecatalogue.data.source.local.LocalDataSource
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.data.source.remote.RemoteDataSource
import com.rizalfahrudin.moviecatalogue.utils.AppExecutor
import com.rizalfahrudin.moviecatalogue.utils.DataDummy
import com.rizalfahrudin.moviecatalogue.utils.LiveDataTestUtil
import com.rizalfahrudin.moviecatalogue.utils.PagedListUtil
import com.rizalfahrudin.moviecatalogue.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieTvRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutor::class.java)

    private val movieTvRepository = FakeMovieTvRepository(remote, local, appExecutors)

    private val movieResponse = DataDummy.generateRemoteDummyMovie()
    private val movieId = movieResponse.movie[0].id!!
    private val movieEntity = DataDummy.generateDataMovie()[0]
    private val position = 0

    @Test
    fun getDataMovieTv() {
        val dataSourceFactor = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieTvEntity>
        `when`(local.getMovieTv(position)).thenReturn(dataSourceFactor)
        movieTvRepository.getDataMovieTv(position)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovie()))
        verify(local).getMovieTv(position)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponse.movie.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDataDetailMovieTv() {
        val dummyMovie = MutableLiveData<MovieTvEntity>()
        dummyMovie.value = DataDummy.generateDataMovie()[0]
        `when`(local.getMovieTvById(movieId)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(movieTvRepository.getDataDetailMovieTv(position, movieId))

        verify(local).getMovieTvById(movieId)

        assertNotNull(movie)
        assertNotNull(movie.data)
        assertEquals(movieEntity, movie.data)

    }

    @Test
    fun getDataMovieTvFavorite() {
        val dataSourceFactor = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieTvEntity>
        `when`(local.getFavoriteMovieTv(position)).thenReturn(dataSourceFactor)
        movieTvRepository.getDataMovieTvFavorite(position)

        val movie = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDataMovie()))
        verify(local).getFavoriteMovieTv(position)
        assertNotNull(movie)
        assertEquals(movieResponse.movie.size.toLong() ,movie.data?.size?.toLong())
    }
}