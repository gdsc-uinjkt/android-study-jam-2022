package com.miko.movieapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miko.movieapp.R
import com.miko.movieapp.databinding.ItemMovieBinding
import com.miko.movieapp.presentation.model.Movie

class MovieAdapter(
    private val items: MutableList<Movie> = mutableListOf(),
    private val onItemClickedCallback: OnItemClickedCallback? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private lateinit var binding: ItemMovieBinding

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            this.binding.apply {
                imgPoster.setImageResource(R.drawable.ic_baseline_image_24)
                tvTitle.text = data.title
                root.setOnClickListener {
                    onItemClickedCallback?.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setItems(items: MutableList<Movie>) {
        this.items.apply {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    interface OnItemClickedCallback{
        fun onItemClicked(data: Movie)
    }
}