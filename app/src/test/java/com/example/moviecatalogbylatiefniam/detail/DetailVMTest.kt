package com.example.moviecatalogbylatiefniam.detail

import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

import org.mockito.junit.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mock
import org.mockito.Mockito.`when`


@RunWith(MockitoJUnitRunner::class)
class DetailVMTest {
    private lateinit var model: DetailVM
    private val dataMovie = Data.generateMovie()[0]
    private val dataTvShow = Data.generateTv()[0]
    private val movieId = dataMovie.movieId
    private val tvId = dataTvShow.tvId

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  movObserver: androidx.lifecycle.Observer<MovieEntity>
    @Mock
    private lateinit var tvObserver: androidx.lifecycle.Observer<TvEntity>


    @Before
    fun setUp(){
    model = DetailVM(repository)
        model.movSelected(movieId.toString())
        model.tvSelected(tvId.toString())

        }

    @Test
    fun getMovies() {
        val mov = MutableLiveData<MovieEntity>()
        mov.value = dataMovie

        `when`(repository.getMovieId(movieId.toString())).thenReturn(mov)
        val movieEntity = repository.getMovieId(movieId.toString()).value as MovieEntity
        verify(repository).getMovieId(movieId.toString())
        assertNotNull(movieEntity)
        assertEquals(dataMovie.movieId, movieEntity.movieId)
        assertEquals(dataMovie.movieTitle, movieEntity.movieTitle)
        assertEquals(dataMovie.movieDescription, movieEntity.movieDescription)
        assertEquals(dataMovie.movieGenre, movieEntity.movieGenre)
        assertEquals(dataMovie.movieDuration, movieEntity.movieDuration)
        assertEquals(dataMovie.moviePicture, movieEntity.moviePicture)

        model.getMovies().observeForever(movObserver)
        verify(movObserver).onChanged(dataMovie)
    }

    @Test
    fun getTvShow() {
        val tvshow = MutableLiveData<TvEntity>()
        tvshow.value = dataTvShow
        `when`(repository.getTvId(tvId.toString())).thenReturn(tvshow)

        model.movSelected(dataTvShow.tvId.toString())
        val tvEntity = repository.getTvId(tvId.toString()).value as TvEntity
        verify(repository).getTvId(tvId.toString())
        assertNotNull(tvEntity)
        assertEquals(dataTvShow.tvId, tvEntity.tvId)
        assertEquals(dataTvShow.tvTitle, tvEntity.tvTitle)
        assertEquals(dataTvShow.tvDescription, tvEntity.tvDescription)
        assertEquals(dataTvShow.tvGenre, tvEntity.tvGenre)
        assertEquals(dataTvShow.tvDuration, tvEntity.tvDuration)
        assertEquals(dataTvShow.tvPicture, tvEntity.tvPicture)

        model.getTvShow().observeForever(tvObserver)
        verify(tvObserver).onChanged(dataTvShow)


    }
}