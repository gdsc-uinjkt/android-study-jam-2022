package com.miko.movieapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ActivityDetailBinding
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.presentation.model.TvSeries

class DetailActivity : AppCompatActivity() {

    companion object{
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
        if(movie != null){
            setMovie(movie!!)
        }else{
            setTvSeries(tvSeries!!)
        }
    }

    private fun setMovie(movie: Movie) {
        with(binding){
            supportActionBar?.title = movie.title
            imgPoster.setImageResource(R.drawable.ic_baseline_image_24)
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
        }
    }

    private fun setTvSeries(tvSeries: TvSeries) {
        with(binding){
            supportActionBar?.title = tvSeries.name
            imgPoster.setImageResource(R.drawable.ic_baseline_image_24)
            tvTitle.text = tvSeries.name
            tvOverview.text = tvSeries.overview
        }
    }

    private fun initIntent() {
        movie = intent.getParcelableExtra(MOVIE_EXTRA)
        tvSeries = intent.getParcelableExtra(TV_SERIES_EXTRA)
    }
}