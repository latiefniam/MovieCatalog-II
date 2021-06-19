package com.example.moviecatalogbylatiefniam.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data

class TvVm(private val repository: Repository): ViewModel() {
    fun getTvShows(): LiveData<List<TvEntity>> = repository.getTvs()
}