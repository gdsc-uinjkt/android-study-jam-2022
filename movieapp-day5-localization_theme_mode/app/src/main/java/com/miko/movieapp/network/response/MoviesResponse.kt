package com.miko.movieapp.network.response


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    val results: List<MovieItem?>?
)