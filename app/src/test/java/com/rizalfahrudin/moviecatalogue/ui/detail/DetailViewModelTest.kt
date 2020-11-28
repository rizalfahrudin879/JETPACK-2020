package com.rizalfahrudin.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.rizalfahrudin.moviecatalogue.core.data.source.MovieTvRepository
import com.rizalfahrudin.moviecatalogue.core.data.source.local.entity.MovieTvEntity
import com.rizalfahrudin.moviecatalogue.core.utils.DataDummy
import com.rizalfahrudin.moviecatalogue.core.vo.Resource
import com.rizalfahrudin.moviecatalogue.detail.DetailViewModel
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
    private lateinit var observer: Observer<Resource<MovieTvEntity>>


    @Before
    fun setUp() {
        viewModel = DetailViewModel(
            movieTvRepository
        )
    }

    @Test
    fun getDataDetailMovie() {
        viewModel.setTypeMovieTv(0, idMovie)

        val dummyDetailMovie =
            Resource.success(DataDummy.generateDummyMovieTvAndSetFavorite(dummyMovie, true))
        val movie = MutableLiveData<Resource<MovieTvEntity>>()
        movie.value = dummyDetailMovie

        `when`(movieTvRepository.getDataDetailMovieTv(0, idMovie)).thenReturn(movie)

        viewModel.movieTvEntity.observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)
    }

    @Test
    fun getDataDetailTv() {
        viewModel.setTypeMovieTv(1, idTv)

        val dummyDetailTv =
            Resource.success(DataDummy.generateDummyMovieTvAndSetFavorite(dummyTv, true))
        val tv = MutableLiveData<Resource<MovieTvEntity>>()
        tv.value = dummyDetailTv

        `when`(movieTvRepository.getDataDetailMovieTv(1, idMovie)).thenReturn(tv)

        viewModel.movieTvEntity.observeForever(observer)
        verify(observer).onChanged(dummyDetailTv)
    }
}