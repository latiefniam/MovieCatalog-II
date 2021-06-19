package com.example.moviecatalogbylatiefniam.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovResponse(
    var movieId: String?,
    var movieTitle: String?,
    var movieDuration: String?,
    var movieGenre:String?,
    var movieDescription:String,
    var moviePicture: String,


    ): Parcelable