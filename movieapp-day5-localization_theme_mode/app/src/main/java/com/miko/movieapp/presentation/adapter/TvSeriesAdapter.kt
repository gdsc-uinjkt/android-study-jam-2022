package com.miko.movieapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ItemTvSeriesBinding
import com.miko.movieapp.presentation.model.TvSeries
import com.miko.movieapp.utils.Const

class TvSeriesAdapter(
    private val items: MutableList<TvSeries> = mutableListOf(),
    private val onItemClickedCallback: OnItemClickedCallback? = null
) : RecyclerView.Adapter<TvSeriesAdapter.TvSeriesViewHolder>() {
    private lateinit var binding: ItemTvSeriesBinding

    inner class TvSeriesViewHolder(private val binding: ItemTvSeriesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TvSeries) {
            this.binding.apply {
                Glide.with(root.context).load("${Const.baseImagerUrl}${data.backdropPath}").error(R.drawable.ic_baseline_image_24).into(imgPoster)
                tvTitle.text = data.name
                root.setOnClickListener {
                    onItemClickedCallback?.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesViewHolder {
        binding = ItemTvSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: List<TvSeries>) {
        this.items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickedCallback {
        fun onItemClicked(data: TvSeries)
    }
}