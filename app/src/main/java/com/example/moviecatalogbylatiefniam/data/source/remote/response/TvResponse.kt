package com.example.moviecatalogbylatiefniam.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class TvResponse (
    var tvId: String?,
    var tvTitle: String?,
    var tvDuration:String?,
    var tvGenre:String?,
    var tvDescription:String?,
    var tvPicture: String

): Parcelable