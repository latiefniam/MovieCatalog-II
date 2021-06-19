package com.example.moviecatalogbylatiefniam.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data
import java.util.*

class MoviesVm(private val repository: Repository):ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = repository.getMovies()
}