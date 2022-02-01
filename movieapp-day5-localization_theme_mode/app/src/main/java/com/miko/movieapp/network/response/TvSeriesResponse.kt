package com.miko.movieapp.network.response


import com.google.gson.annotations.SerializedName

data class TvSeriesResponse(
    @SerializedName("results")
    val results: List<TvSeriesItem>?
)