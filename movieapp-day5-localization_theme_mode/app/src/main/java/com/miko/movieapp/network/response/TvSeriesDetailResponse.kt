package com.miko.movieapp.network.response


import com.google.gson.annotations.SerializedName

data class TvSeriesDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("overview")
    val overview: String?
)