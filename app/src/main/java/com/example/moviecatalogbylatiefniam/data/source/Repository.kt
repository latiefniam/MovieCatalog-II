package com.example.moviecatalogbylatiefniam.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogbylatiefniam.data.MovieEntity
import com.example.moviecatalogbylatiefniam.data.TvEntity
import com.example.moviecatalogbylatiefniam.data.source.remote.RemoteDataSource
import com.example.moviecatalogbylatiefniam.data.source.remote.response.MovResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.TvResponse

class Repository (private val remoteDS: RemoteDataSource):DataSource {

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(remoteDS: RemoteDataSource):Repository
        = instance?: synchronized(this){
            instance?: Repository(remoteDS)
        }

    }

    override fun getMovies(): LiveData<List<MovieEntity>> {
        val movie = MutableLiveData<List<MovieEntity>>()
        remoteDS.getMovies(object :RemoteDataSource.LoadMovieCb{
            override fun onAllMovieReceived(responseMov: List<MovResponse>) {
             val list = ArrayList<MovieEntity>()
                for (response in responseMov){
                    val mov = MovieEntity(
                        response.movieId, response.movieTitle,response.movieDuration,
                        response.movieGenre, response.movieDescription, response.moviePicture
                    )
                    list.add(mov)
                }
                movie.postValue(list)
            }
        })
        return movie
        }
    override fun getMovieId(movieId: String): LiveData<MovieEntity> {
        val movie = MutableLiveData<MovieEntity>()
        remoteDS.getMovies(object :RemoteDataSource.LoadMovieCb{
            override fun onAllMovieReceived(responseMov: List<MovResponse>) {
                lateinit var list :MovieEntity
                for (response in responseMov){
                    if (response.movieId==movieId){
                     list = MovieEntity(
                        response.movieId, response.movieTitle,response.movieDuration,
                        response.movieGenre, response.movieDescription, response.moviePicture
                    )

                }
                }
                movie.postValue(list)
            }
        })
        return movie


    }



    override fun getTvs(): LiveData<List<TvEntity>> {
        val tv = MutableLiveData<List<TvEntity>>()
        remoteDS.getTvShows(object : RemoteDataSource.LoadTvshowCb{
            override fun onAllTvshowReceived(responseTv: List<TvResponse>) {
                val list = ArrayList<TvEntity>()
                for (response in responseTv){
                    val tvShow = TvEntity(
                        response.tvId,response.tvTitle,response.tvDuration,
                        response.tvGenre,response.tvDescription,response.tvPicture
                    )
                    list.add(tvShow)
                }
                tv.postValue(list)
            }
        })
        return tv
    }



    override fun getTvId(tvId: String): LiveData<TvEntity> {
        val tv = MutableLiveData<TvEntity>()
        remoteDS.getTvShows(object : RemoteDataSource.LoadTvshowCb{
            override fun onAllTvshowReceived(responseTv: List<TvResponse>) {
                lateinit var list : TvEntity
                for (response in responseTv){
                    if (response.tvId == tvId) {
                        list = TvEntity(
                            response.tvId, response.tvTitle, response.tvDuration,
                            response.tvGenre, response.tvDescription, response.tvPicture
                        )
                    }
                }
                tv.postValue(list)
            }

        })
        return tv
    }


}