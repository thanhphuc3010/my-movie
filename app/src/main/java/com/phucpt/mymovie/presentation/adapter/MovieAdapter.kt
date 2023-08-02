package com.phucpt.mymovie.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.phucpt.mymovie.codebase.BaseAdapter
import com.phucpt.mymovie.database.entity.MovieEntity
import com.phucpt.mymovie.databinding.ItemMovieBinding
import javax.inject.Inject

/**
 * Created by Phucpt on 09/07/2023 at 17:52
 */

class MovieAdapter @Inject constructor() :
    PagingDataAdapter<MovieEntity, MovieAdapter.BaseViewHolder>(MOVIE_COMPARATOR) {
    private lateinit var onItemClicked: (MovieEntity) -> Unit
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViews(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    fun setOnItemClickListener(listener: (MovieEntity) -> Unit) {
        this.onItemClicked = listener
    }

    inner class BaseViewHolder(binding: ItemMovieBinding) :
        BaseAdapter.BaseViewHolder<ItemMovieBinding>(binding) {
        override fun bindViews(position: Int) {
            val movie = getItem(position) ?: return

            binding.root.setOnClickListener {
                onItemClicked.invoke(movie)
            }

            binding.txtTitle.text = movie.title
            Glide.with(binding.root.context)
                .load(Uri.parse("https://image.tmdb.org/t/p/w500${movie.posterPath}"))
                .into(binding.imgPoster)
        }
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity) =
                oldItem == newItem
        }
    }
}