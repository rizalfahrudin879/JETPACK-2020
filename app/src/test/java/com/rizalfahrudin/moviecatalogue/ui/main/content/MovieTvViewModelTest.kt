package com.rizalfahrudin.moviecatalogue.ui.main.content

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieTvViewModelTest {
    private lateinit var viewModel: MovieTvViewModel

    @Before
    fun setUp() {
        viewModel = MovieTvViewModel()
    }

    @Test
    fun getDataMovie() {
        val movieEntities = viewModel.getDataMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities.size)
    }

    @Test
    fun getDataTv() {
        val tvEntities = viewModel.getDataTv()
        assertNotNull(tvEntities)
        assertEquals(10, tvEntities.size)
    }
}