package com.miko.movieapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miko.movieapp.databinding.FragmentMovieBinding
import com.miko.movieapp.network.RemoteDataSource
import com.miko.movieapp.network.response.MoviesResponse
import com.miko.movieapp.presentation.activity.DetailActivity
import com.miko.movieapp.presentation.adapter.MovieAdapter
import com.miko.movieapp.presentation.model.Movie
import com.miko.movieapp.utils.Mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            onItemClickedCallback = object : MovieAdapter.OnItemClickedCallback {
                override fun onItemClicked(data: Movie) {
                    DetailActivity.start(requireContext(), data)
                }
            }
        )

        initRecyclerView()

        RemoteDataSource.getInstance().getMovies().enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                response.body()?.let {
                    movieAdapter.setItems(Mapper.toMovies(it))
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        with(binding) {
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