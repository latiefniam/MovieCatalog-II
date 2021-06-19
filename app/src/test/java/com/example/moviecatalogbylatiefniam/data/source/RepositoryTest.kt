package com.example.moviecatalogbylatiefniam.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.utils.Data
import com.example.moviecatalogbylatiefniam.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

import org.junit.Test
import org.junit.Rule

import org.mockito.Mockito.mock



class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val repository = FakeRepositoryTest(remote)
    private val movResponse = Data.generateRemoteMovie()
    private val movieId = movResponse[0].movieId
    private val tvResponse = Data.generateRemoteTv()
    private val tvId = tvResponse[0].tvId



    @Test
    fun getMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovieCb)
                .onAllMovieReceived(movResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(repository.getMovies())
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertEquals(movResponse.size.toLong(), movieEntity.size.toLong())

    }


    @Test
    fun getMovieId() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCb)
                .onAllMovieReceived(movResponse)
            null
        }.`when`(remote).getMovies(any())
        val movieEntity = LiveDataTestUtil.getValue(repository.getMovieId(movieId.toString()))
        verify(remote).getMovies(any())
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.movieTitle)
        assertNotNull(movResponse[0].movieTitle, movieEntity.movieTitle)

    }

    @Test
    fun getTvs() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvshowCb)
                .onAllTvshowReceived(tvResponse)
            null
        }.`when`(remote).getTvShows(any())
        val tvEntity = LiveDataTestUtil.getValue(repository.getTvs())
        verify(remote).getTvShows(any())
        assertNotNull(tvEntity)
        assertEquals(tvResponse.size.toLong(), tvEntity.size.toLong())

    }

    @Test
    fun getTvId() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvshowCb)
                .onAllTvshowReceived(tvResponse)
            null
        }.`when`(remote).getTvShows(any())
        val tvEntity = LiveDataTestUtil.getValue(repository.getTvId(tvId.toString()))
        verify(remote).getTvShows(any())
        assertNotNull(tvEntity)
        assertNotNull(tvEntity.tvTitle)
        assertNotNull(tvResponse[0].tvTitle, tvEntity.tvTitle)


    }
}