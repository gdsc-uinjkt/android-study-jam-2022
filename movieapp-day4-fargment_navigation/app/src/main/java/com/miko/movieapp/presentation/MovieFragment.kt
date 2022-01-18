package com.miko.movieapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miko.movieapp.databinding.FragmentMovieBinding
import com.miko.movieapp.presentation.adapter.MovieAdapter
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.utils.convertStringToMovie
import com.miko.movieapp.utils.readJson

class MovieFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            MovieFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(
            items = convertStringToMovie(requireContext().readJson("movie.json")).toMutableList(),
            onItemClickedCallback = object : MovieAdapter.OnItemClickedCallback{
                override fun onItemClicked(data: Movie) {
                    DetailActivity.start(requireContext(), data)
                }
            }
        )

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding){
            rvMovie.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = movieAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                )
            }
        }
    }
}