package com.miko.movieapp.network

import com.miko.movieapp.network.response.MovieDetailResponse
import com.miko.movieapp.network.response.MoviesResponse
import com.miko.movieapp.network.response.TvSeriesDetailResponse
import com.miko.movieapp.network.response.TvSeriesResponse
import com.miko.movieapp.utils.Const
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("discover/movie?api_key=${Const.apiKey}")
    fun getMovies(): Call<MoviesResponse>

    @GET("discover/tv?api_key=${Const.apiKey}")
    fun getTvSeries(): Call<TvSeriesResponse>

    @GET("movie/{id}?api_key=${Const.apiKey}")
    fun getMovieDetail(@Path("id") id: Int): Call<MovieDetailResponse>

    @GET("tv/{id}?api_key=${Const.apiKey}")
    fun getTvSeriesDetail(@Path("id") id: Int): Call<TvSeriesDetailResponse>
}