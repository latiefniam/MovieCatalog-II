package com.example.moviecatalogbylatiefniam.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class MoviesVmTest {
    private lateinit var model: MoviesVm
    private val dataMovie = Data.generateMovie()
    private val movie = MutableLiveData<List<MovieEntity>>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  movObserver: androidx.lifecycle.Observer<List<MovieEntity>>



    @Before
    fun setUp(){
        model = MoviesVm(repository)

    }

    @Test
    fun getMovies() {
        movie.value = dataMovie
        `when`(repository.getMovies()).thenReturn(movie)
        val movieEntity = model.getMovies().value
        verify(repository).getMovies()
        assertNotNull(movieEntity)
        assertEquals(11, movieEntity?.size)

        model.getMovies().observeForever(movObserver)
        verify(movObserver).onChanged(dataMovie)

    }
}