package com.rizalfahrudin.moviecatalogue.ui.main.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieTvViewModelTest {
    private lateinit var viewModel: MovieTvViewModel

    @get:Rule
    var instantTaskRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var observer: Observer<List<MovieTvEntity>>

    @Before
    fun setUp() {
        viewModel = MovieTvViewModel(movieTvRepository)
    }

    @Test
    fun getDataMovie() {
        viewModel.setTypeMovieTv(0)
        val dummyMovie = DataDummy.generateDataMovie()
        val movie = MutableLiveData<List<MovieTvEntity>>()
        movie.value = dummyMovie

        `when`(movieTvRepository.getDataMovieTv(0)).thenReturn(movie)
        val movieEntities = viewModel.getDataMovieTv().value
        verify(movieTvRepository).getDataMovieTv(0)
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getDataMovieTv().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDataTv() {
        viewModel.setTypeMovieTv(1)
        val dummyTv = DataDummy.generateDataTv()
        val tv = MutableLiveData<List<MovieTvEntity>>()
        tv.value = dummyTv

        `when`(movieTvRepository.getDataMovieTv(1)).thenReturn(tv)
        val tvEntities = viewModel.getDataMovieTv().value
        verify(movieTvRepository).getDataMovieTv(1)
        assertNotNull(tvEntities)
        assertEquals(20, tvEntities?.size)

        viewModel.getDataMovieTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}