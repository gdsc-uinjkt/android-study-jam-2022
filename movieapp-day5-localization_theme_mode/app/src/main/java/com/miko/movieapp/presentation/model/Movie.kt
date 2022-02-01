package com.miko.movieapp.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val overview: String,
    val title: String,
    val backdropPath: String,
    val id: Int
) : Parcelable