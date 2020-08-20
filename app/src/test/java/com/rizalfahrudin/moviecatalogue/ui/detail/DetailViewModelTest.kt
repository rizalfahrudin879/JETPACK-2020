package com.rizalfahrudin.moviecatalogue.ui.detail

import com.rizalfahrudin.moviecatalogue.utils.Data
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = Data.generateDataMovie()[0]
    private val dummyTv = Data.generateDataTv()[0]

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setIdData(1)

    }

    @Test
    fun getDataMovie() {
        val movieEntities = viewModel.getDataMovie()
        assertNotNull(movieEntities)
        assertEquals(movieEntities, dummyMovie)
    }

    @Test
    fun getDataTv() {
        val tvEntities = viewModel.getDataTv()
        assertNotNull(tvEntities)
        assertEquals(tvEntities, dummyTv)
    }
}