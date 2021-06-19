package com.example.moviecatalogbylatiefniam.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.TvEntity
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
class TvVmTest {
    private lateinit var model: TvVm
    private val dataTv = Data.generateTv()
    private val tvShow = MutableLiveData<List<TvEntity>>()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository
    @Mock
    private lateinit var  tvObserver: androidx.lifecycle.Observer<List<TvEntity>>




    @Before
    fun setUp(){
        model = TvVm(repository)
    }
    @Test
    fun getTvShows() {
        tvShow.value = dataTv
        `when`(repository.getTvs()).thenReturn(tvShow)

        val tvShowEntity = model.getTvShows().value
        verify(repository).getTvs()
        assertNotNull(tvShowEntity)
        assertEquals(11,tvShowEntity?.size)

        model.getTvShows().observeForever(tvObserver)
        verify(tvObserver).onChanged(dataTv)

    }
}