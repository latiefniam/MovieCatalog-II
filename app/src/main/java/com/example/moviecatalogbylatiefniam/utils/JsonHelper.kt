package com.example.moviecatalogbylatiefniam.utils

import android.content.Context
import com.example.moviecatalogbylatiefniam.data.source.remote.response.MovResponse
import com.example.moviecatalogbylatiefniam.data.source.remote.response.TvResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper (private val context: Context){

    private fun parsingToString(contentName: String):String?{
        return try {
            val `is` = context.assets.open(contentName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }catch (ex:IOException){
            ex.printStackTrace()
            null
        }
    }
    fun loadMovie():List<MovResponse>{
        val movList = ArrayList<MovResponse>()
        try {
            val response = JSONObject(parsingToString("Content.json"))
            val listArray = response.getJSONArray("Movie")
            for (i in 0 until listArray.length()){
                val movie = listArray.getJSONObject(i)

                with(movie){
                    val movieId = getString("movieId")
                    val movieTitle = getString("movieTitle")
                    val movieDuration = getString("movieDuration")
                    val movieGenre = getString("movieGenre")
                    val movieDescription = getString("movieDescription")
                    val moviePicture = getString("moviePicture")

                    val movResponse = MovResponse(movieId,movieTitle,movieDuration,movieGenre,movieDescription,moviePicture)
                    movList.add(movResponse)
                }
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }
        return movList
    }

    fun loadTv():List<TvResponse>{
        val tvList = ArrayList<TvResponse>()
        try {
            val response = JSONObject(parsingToString("Content.json"))
            val listArray = response.getJSONArray("TvShow")
            for (i in 0 until listArray.length()){
                val tvShow = listArray.getJSONObject(i)

                with(tvShow){
                    val tvId = getString("tvId")
                    val tvTitle = getString("tvTitle")
                    val tvDuration = getString("tvDuration")
                    val tvGenre = getString("tvGenre")
                    val tvDescription = getString("tvDescription")
                    val tvPicture = getString("tvPicture")

                    val tvResponse = TvResponse(tvId,tvTitle,tvDuration,tvGenre,tvDescription,tvPicture)
                    tvList.add(tvResponse)
                }
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }
        return tvList
    }
}

