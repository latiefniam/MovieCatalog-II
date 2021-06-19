package com.example.moviecatalogbylatiefniam.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TvEntity(
    var tvId: String?,
    var tvTitle: String?,
    var tvDuration:String?,
    var tvGenre:String?,
    var tvDescription:String?,
    var tvPicture: String

):Parcelable
