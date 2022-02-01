package com.miko.movieapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeries(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val posterPath: String,
    val originCountry: List<String>,
    val backdropPath: String,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
    val name: String,
    val id: Int,
    val voteCount: Int
) : Parcelable