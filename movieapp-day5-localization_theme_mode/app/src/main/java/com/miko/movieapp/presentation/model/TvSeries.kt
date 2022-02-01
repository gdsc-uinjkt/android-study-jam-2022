package com.miko.movieapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvSeries(
    val overview: String,
    val backdropPath: String,
    val name: String,
    val id: Int
) : Parcelable