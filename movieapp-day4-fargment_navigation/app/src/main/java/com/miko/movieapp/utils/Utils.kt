package com.miko.movieapp.utils

import android.content.Context
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.presentation.model.TvSeries

fun Context.readJson(fileName: String): String {
    return assets.open(fileName).bufferedReader().use { it.readText() }
}

fun convertStringToMovie(jsonString: String): List<Movie> {
    val jsonArray: JsonArray = JsonParser().parse(jsonString).asJsonArray
    val movies: MutableList<Movie> = mutableListOf()
    jsonArray.forEach {
        val overview: String = it.asJsonObject["overview"].asString
        val originalLanguage: String = it.asJsonObject["original_language"].asString
        val originalTitle: String = it.asJsonObject["original_title"].asString
        val video: Boolean = it.asJsonObject["video"].asBoolean
        val title: String = it.asJsonObject["title"].asString
        val genreIds: MutableList<Int> = mutableListOf()
        it.asJsonObject.get("genre_ids").asJsonArray.forEach { genreId ->
            genreIds.add(genreId.asInt)
        }
        val posterPath: String = it.asJsonObject["poster_path"].asString
        val backdropPath: String = it.asJsonObject["backdrop_path"].asString
        val releaseDate = it.asJsonObject["release_date"].asString
        val popularity: Double = it.asJsonObject["popularity"].asDouble
        val voteAverage: Double = it.asJsonObject["vote_average"].asDouble
        val id: Int = it.asJsonObject["id"].asInt
        val adult: Boolean = it.asJsonObject["adult"].asBoolean
        val voteCount: Int = it.asJsonObject["vote_count"].asInt
        movies.add(
            Movie(
                overview = overview,
                originalLanguage = originalLanguage,
                originalTitle = originalTitle,
                video = video,
                title = title,
                genreIds = genreIds,
                posterPath = posterPath,
                backdropPath = backdropPath,
                releaseDate = releaseDate,
                popularity = popularity,
                voteAverage = voteAverage,
                id = id,
                adult = adult,
                voteCount = voteCount
            )
        )
    }
    return movies
}

fun convertStringToTvSeries(jsonString: String): List<TvSeries> {
    val jsonArray: JsonArray = JsonParser().parse(jsonString).asJsonArray
    val tvs: MutableList<TvSeries> = mutableListOf()
    jsonArray.forEach {
        val overview: String = it.asJsonObject["overview"].asString
        val originalLanguage: String = it.asJsonObject["original_language"].asString
        val originalTitle: String = it.asJsonObject["original_name"].asString
        val title: String = it.asJsonObject["name"].asString
        val genreIds: MutableList<Int> = mutableListOf()
        it.asJsonObject.get("genre_ids").asJsonArray.forEach { genreId ->
            genreIds.add(genreId.asInt)
        }
        val originCountries: MutableList<String> = mutableListOf()
        it.asJsonObject.get("origin_country").asJsonArray.forEach { originCountry ->
            originCountries.add(originCountry.asString)
        }
        val posterPath: String = it.asJsonObject["poster_path"].asString
        val backdropPath: String = it.asJsonObject["backdrop_path"].asString
        val releaseDate = it.asJsonObject["first_air_date"].asString
        val popularity: Double = it.asJsonObject["popularity"].asDouble
        val voteAverage: Double = it.asJsonObject["vote_average"].asDouble
        val id: Int = it.asJsonObject["id"].asInt
        val voteCount: Int = it.asJsonObject["vote_count"].asInt
        tvs.add(
            TvSeries(
                overview = overview,
                originalLanguage = originalLanguage,
                originalName = originalTitle,
                name = title,
                genreIds = genreIds,
                posterPath = posterPath,
                backdropPath = backdropPath,
                firstAirDate = releaseDate,
                popularity = popularity,
                voteAverage = voteAverage,
                id = id,
                voteCount = voteCount,
                originCountry = originCountries
            )
        )
    }
    return tvs
}