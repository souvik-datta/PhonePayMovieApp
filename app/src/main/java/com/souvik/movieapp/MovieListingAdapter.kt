package com.souvik.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.souvik.movieapp.databinding.MovieRowLayoutBinding

class MovieListingAdapter(
    var list: ArrayList<Movie>,
    val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieListingAdapter.MovieRow>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListingAdapter.MovieRow {
        val binding =
            MovieRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieRow(binding)
    }

    override fun onBindViewHolder(holder: MovieListingAdapter.MovieRow, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onClick(adaptePostion = position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MovieRow(val binding: MovieRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            Glide.with(binding.ivPosterImg.context)
                .load("https://image.tmdb.org/t/p/w500${data.backdrop_path}")
                .into(binding.ivPosterImg)

            binding.tvTitle.text = data.title ?: ""
            binding.tvShortDesc.text = data.overview ?: ""
        }
    }

    interface OnMovieClickListener {
        fun onClick(adaptePostion: Int)
    }
}