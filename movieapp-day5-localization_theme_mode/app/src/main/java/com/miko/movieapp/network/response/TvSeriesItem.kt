package com.miko.movieapp.network.response


import com.google.gson.annotations.SerializedName

data class TvSeriesItem(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)