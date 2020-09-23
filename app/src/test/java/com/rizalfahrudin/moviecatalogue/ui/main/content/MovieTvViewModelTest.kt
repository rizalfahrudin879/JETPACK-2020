package com.rizalfahrudin.moviecatalogue.ui.main.content

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.rizalfahrudin.moviecatalogue.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.vo.Resource
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
    private lateinit var observer: Observer<Resource<PagedList<MovieTvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieTvEntity>

    @Before
    fun setUp() {
        viewModel = MovieTvViewModel(movieTvRepository)
    }

    @Test
    fun getDataMovie() {
        viewModel.setTypeMovieTv(0)
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)

        val movie = MutableLiveData<Resource<PagedList<MovieTvEntity>>>()
        movie.value = dummyMovie

        `when`(movieTvRepository.getDataMovieTv(0)).thenReturn(movie)
        val movieEntities = viewModel.getDataMovieTv().value?.data
        verify(movieTvRepository).getDataMovieTv(0)
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getDataMovieTv().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getDataTv() {
        viewModel.setTypeMovieTv(1)
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)

        val tv = MutableLiveData<Resource<PagedList<MovieTvEntity>>>()
        tv.value = dummyTv

        `when`(movieTvRepository.getDataMovieTv(1)).thenReturn(tv)
        val tvEntities = viewModel.getDataMovieTv().value?.data
        verify(movieTvRepository).getDataMovieTv(1)
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getDataMovieTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}