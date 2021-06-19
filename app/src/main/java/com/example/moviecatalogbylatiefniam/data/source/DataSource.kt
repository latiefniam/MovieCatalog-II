package com.example.moviecatalogbylatiefniam.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity

interface DataSource {
    fun getMovies(): LiveData<List<MovieEntity>>
    fun getMovieId(movieId:String):LiveData<MovieEntity>
    fun getTvs():LiveData<List<TvEntity>>
    fun getTvId(tvId: String):LiveData<TvEntity>
}