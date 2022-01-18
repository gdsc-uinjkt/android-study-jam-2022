package com.miko.movieapp.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ActivityDetailBinding
import com.miko.movieapp.presentation.model.Movie

class DetailActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_EXTRA = "movie_extra"

        @JvmStatic
        fun start(context: Context, data: Movie) {

            val starter = Intent(context, DetailActivity::class.java)
                .putExtra(MOVIE_EXTRA, data)
            context.startActivity(starter)
        }
    }
    private lateinit var binding: ActivityDetailBinding
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initIntent()
        setMovie(movie)
    }

    private fun setMovie(movie: Movie) {
        with(binding){
            supportActionBar?.title = movie.title
            imgPoster.setImageResource(R.drawable.ic_baseline_image_24)
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
        }
    }

    private fun initIntent() {
        movie = intent.getParcelableExtra(MOVIE_EXTRA)!!
    }
}