package com.miko.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miko.movieapp.utils.convertStringToModel
import com.miko.movieapp.databinding.ActivityMainBinding
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.utils.readJson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieAdapter = MovieAdapter(
            items = convertStringToModel(readJson("response.json")).toMutableList(),
            onItemClickedCallback = object : MovieAdapter.OnItemClickedCallback{
                override fun onItemClicked(data: Movie) {
                    DetailActivity.start(this@MainActivity, data)
                }
            }
        )

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding){
            rvSearch.apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
                )
            }
        }
    }
}