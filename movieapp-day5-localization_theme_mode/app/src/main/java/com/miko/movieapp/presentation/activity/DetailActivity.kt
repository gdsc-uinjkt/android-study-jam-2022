package com.miko.movieapp.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ActivityDetailBinding
import com.miko.movieapp.network.RemoteDataSource
import com.miko.movieapp.network.response.MovieDetailResponse
import com.miko.movieapp.network.response.TvSeriesDetailResponse
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.presentation.model.TvSeries
import com.miko.movieapp.utils.Const
import com.miko.movieapp.utils.Mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    companion object {
        private const val MOVIE_EXTRA = "movie_extra"
        private const val TV_SERIES_EXTRA = "tv_series_extra"

        @JvmStatic
        fun start(context: Context, data: Movie) {

            val starter = Intent(context, DetailActivity::class.java)
                .putExtra(MOVIE_EXTRA, data)
            context.startActivity(starter)
        }

        fun start(context: Context, data: TvSeries) {

            val starter = Intent(context, DetailActivity::class.java)
                .putExtra(TV_SERIES_EXTRA, data)
            context.startActivity(starter)
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private var movie: Movie? = null
    private var tvSeries: TvSeries? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initIntent()
        if (movie != null) {
            RemoteDataSource.getInstance().getMovieDetail(movie!!.id).enqueue(object : Callback<MovieDetailResponse> {
                override fun onResponse(call: Call<MovieDetailResponse>, response: Response<MovieDetailResponse>) {
                    response.body()?.let {
                        setMovie(Mapper.toMovieDetail(it))
                    }
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            RemoteDataSource.getInstance().getTvSeriesDetail(tvSeries!!.id).enqueue(object : Callback<TvSeriesDetailResponse> {
                override fun onResponse(call: Call<TvSeriesDetailResponse>, response: Response<TvSeriesDetailResponse>) {
                    response.body()?.let {
                        setTvSeries(Mapper.toTvSeriesDetail(it))
                    }
                }

                override fun onFailure(call: Call<TvSeriesDetailResponse>, t: Throwable) {
                    Toast.makeText(this@DetailActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setMovie(movie: Movie) {
        with(binding) {
            supportActionBar?.title = movie.title
            Glide.with(root.context).load("${Const.baseImagerUrl}${movie.backdropPath}").error(R.drawable.ic_baseline_image_24).into(imgPoster)
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
        }
    }

    private fun setTvSeries(tvSeries: TvSeries) {
        with(binding) {
            supportActionBar?.title = tvSeries.name
            Glide.with(root.context).load("${Const.baseImagerUrl}${tvSeries.backdropPath}").error(R.drawable.ic_baseline_image_24).into(imgPoster)
            tvTitle.text = tvSeries.name
            tvOverview.text = tvSeries.overview
        }
    }

    private fun initIntent() {
        movie = intent.getParcelableExtra(MOVIE_EXTRA)
        tvSeries = intent.getParcelableExtra(TV_SERIES_EXTRA)
    }
}