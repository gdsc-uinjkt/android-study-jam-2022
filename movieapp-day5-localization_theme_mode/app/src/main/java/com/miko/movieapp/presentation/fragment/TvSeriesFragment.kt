package com.miko.movieapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miko.movieapp.databinding.FragmentTvSeriesBinding
import com.miko.movieapp.network.RemoteDataSource
import com.miko.movieapp.network.response.TvSeriesResponse
import com.miko.movieapp.presentation.activity.DetailActivity
import com.miko.movieapp.presentation.adapter.TvSeriesAdapter
import com.miko.movieapp.presentation.model.TvSeries
import com.miko.movieapp.utils.Mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvSeriesFragment : Fragment() {

    private lateinit var binding: FragmentTvSeriesBinding
    private lateinit var tvSeriesAdapter: TvSeriesAdapter

    companion object {
        @JvmStatic
        fun newInstance() =
            TvSeriesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSeriesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSeriesAdapter = TvSeriesAdapter(
            onItemClickedCallback = object : TvSeriesAdapter.OnItemClickedCallback {
                override fun onItemClicked(data: TvSeries) {
                    DetailActivity.start(requireContext(), data)
                }
            }
        )

        initRecyclerView()

        RemoteDataSource.getInstance().getTvSeries().enqueue(object : Callback<TvSeriesResponse>{
            override fun onResponse(call: Call<TvSeriesResponse>, response: Response<TvSeriesResponse>) {
                response.body()?.let {
                    tvSeriesAdapter.setItems(Mapper.toTvSeries(it))
                }
            }

            override fun onFailure(call: Call<TvSeriesResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecyclerView() {
        with(binding) {
            rvTvSeries.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = tvSeriesAdapter
                addItemDecoration(
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
                )
            }
        }
    }
}