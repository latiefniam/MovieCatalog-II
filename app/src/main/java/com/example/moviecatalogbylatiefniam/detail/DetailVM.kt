package com.example.moviecatalogbylatiefniam.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.Repository
import com.example.moviecatalogbylatiefniam.utils.Data

class DetailVM(private val repository: Repository): ViewModel() {

    private lateinit var movId: String
    private lateinit var tvId : String

    fun movSelected (movId: String){
        this.movId= movId
    }
    fun tvSelected(tvId: String){
        this.tvId = tvId
    }
    fun getMovies(): LiveData<MovieEntity>
    = repository.getMovieId(movId)
    fun getTvShow (): LiveData<TvEntity>
    = repository.getTvId(tvId)
}