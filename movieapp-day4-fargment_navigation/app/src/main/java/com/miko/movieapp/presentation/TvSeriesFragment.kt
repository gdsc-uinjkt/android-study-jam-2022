package com.miko.movieapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.miko.movieapp.databinding.FragmentTvSeriesBinding
import com.miko.movieapp.presentation.adapter.TvSeriesAdapter
import com.miko.movieapp.presentation.model.TvSeries
import com.miko.movieapp.utils.convertStringToTvSeries
import com.miko.movieapp.utils.readJson

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
            items = convertStringToTvSeries(requireContext().readJson("tv_series.json")).toMutableList(),
            onItemClickedCallback = object : TvSeriesAdapter.OnItemClickedCallback {
                override fun onItemClicked(data: TvSeries) {
                    DetailActivity.start(requireContext(), data)
                }
            }
        )

        initRecyclerView()
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