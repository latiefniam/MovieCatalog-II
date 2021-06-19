package com.example.moviecatalogbylatiefniam.data.source.remote

import android.os.Looper
import com.example.moviecatalogbylatiefniam.data.source.remote.response.MovResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.TvResponse
import com.example.moviecatalogbylatiefniam.utils.Espressoldling
import com.example.moviecatalogbylatiefniam.utils.JsonHelper



class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    private val handler = android.os.Handler(Looper.getMainLooper())

    companion object{
        private const val SERVICE_LATENCY:Long = 1000

        @Volatile
        private var instance :RemoteDataSource? = null

        fun getInstance(helper: JsonHelper):RemoteDataSource=
            instance?: synchronized(this){
                instance?: RemoteDataSource(helper)
            }
    }

    fun getMovies(callback:LoadMovieCb){
        Espressoldling.increment()
        handler.postDelayed({
            callback.onAllMovieReceived(jsonHelper.loadMovie())
            Espressoldling.decrement()
        }, SERVICE_LATENCY)
    }
    //fun getMovies(param: LoadMovieCb): List<MovResponse> = jsonHelper.loadMovie()
    fun getTvShows(callback: LoadTvshowCb){
        Espressoldling.increment()
        handler.postDelayed({
            callback.onAllTvshowReceived(jsonHelper.loadTv())
            Espressoldling.decrement()
        }, SERVICE_LATENCY)
    }
    interface LoadMovieCb {
        fun onAllMovieReceived(responseMov: List<MovResponse>)
    }

    interface LoadTvshowCb {
        fun onAllTvshowReceived(responseTv: List<TvResponse>)
    }
}