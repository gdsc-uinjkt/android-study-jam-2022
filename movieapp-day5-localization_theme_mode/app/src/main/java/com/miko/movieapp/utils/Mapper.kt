package com.miko.movieapp.utils

import com.miko.movieapp.network.response.MovieDetailResponse
import com.miko.movieapp.network.response.MoviesResponse
import com.miko.movieapp.network.response.TvSeriesDetailResponse
import com.miko.movieapp.network.response.TvSeriesResponse
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.presentation.model.TvSeries

object Mapper {
    fun toMovies(moviesResponse: MoviesResponse): List<Movie>{
        val movies: MutableList<Movie> = mutableListOf()
        var movie: Movie
        moviesResponse.results?.forEach {
            it?.let { movieResponseItem ->
                movie = Movie(
                    overview = "",
                    title = movieResponseItem.title ?: "",
                    backdropPath = movieResponseItem.backdropPath ?: "",
                    id = movieResponseItem.id ?: 0
                )
                movies.add(movie)
            }
        }
        return movies
    }

    fun toTvSeries(tvSeriesResponse: TvSeriesResponse): List<TvSeries>{
        val tvSeriess: MutableList<TvSeries> = mutableListOf()
        var tvSeries: TvSeries
        tvSeriesResponse.results?.forEach {
            it?.let { tvSeriesResponseItem ->
                tvSeries = TvSeries(
                    overview = "",
                    name = tvSeriesResponseItem.name ?: "",
                    backdropPath = tvSeriesResponseItem.backdropPath ?: "",
                    id = tvSeriesResponseItem.id ?: 0
                )
                tvSeriess.add(tvSeries)
            }
        }
        return tvSeriess
    }

    fun toMovieDetail(movieDetailResponse: MovieDetailResponse): Movie{
        return Movie(
            overview = movieDetailResponse.overview ?: "",
            title = movieDetailResponse.title ?: "",
            backdropPath = movieDetailResponse.backdropPath ?: "",
            id = movieDetailResponse.id ?: 0
        )
    }

    fun toTvSeriesDetail(movieDetailResponse: TvSeriesDetailResponse): TvSeries{
        return TvSeries(
            overview = movieDetailResponse.overview ?: "",
            name = movieDetailResponse.name ?: "",
            backdropPath = movieDetailResponse.backdropPath ?: "",
            id = movieDetailResponse.id ?: 0
        )
    }
}