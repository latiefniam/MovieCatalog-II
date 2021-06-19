package com.example.moviecatalogbylatiefniam.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var movieId: String?,
    var movieTitle: String?,
    var movieDuration: String?,
    var movieGenre:String?,
    var movieDescription:String,
    var moviePicture: String,


    ):Parcelable

