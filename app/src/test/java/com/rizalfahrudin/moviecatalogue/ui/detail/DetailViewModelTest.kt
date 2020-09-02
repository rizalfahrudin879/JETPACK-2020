package com.rizalfahrudin.moviecatalogue.ui.detail

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
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDataMovie()[0]
    private val idMovie = dummyMovie.id!!

    private val dummyTv = DataDummy.generateDataTv()[0]
    private val idTv = dummyMovie.id!!

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieTvRepository: MovieTvRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieTvEntity>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieTvRepository)
    }

    @Test
    fun getDataDetailMovie() {
        viewModel.setTypeMovieTv(0, idMovie)

        val movie = MutableLiveData<MovieTvEntity>()
        movie.value = dummyMovie

        `when`(movieTvRepository.getDataDetailMovieTv(0, idMovie)).thenReturn(movie)
        val movieEntity = viewModel.getDataMovie().value as MovieTvEntity
        verify(movieTvRepository).getDataDetailMovieTv(0, idMovie)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.image, movieEntity.image)

        viewModel.getDataMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDataDetailTv() {
        viewModel.setTypeMovieTv(1, idTv)

        val movie = MutableLiveData<MovieTvEntity>()
        movie.value = dummyTv

        `when`(movieTvRepository.getDataDetailMovieTv(1, idTv)).thenReturn(movie)
        val tvEntity = viewModel.getDataMovie().value as MovieTvEntity
        verify(movieTvRepository).getDataDetailMovieTv(1, idTv)
        assertNotNull(tvEntity)
        assertEquals(dummyTv.id, tvEntity.id)
        assertEquals(dummyTv.title, tvEntity.title)
        assertEquals(dummyTv.description, tvEntity.description)
        assertEquals(dummyTv.image, tvEntity.image)

        viewModel.getDataMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyTv)
    }
}