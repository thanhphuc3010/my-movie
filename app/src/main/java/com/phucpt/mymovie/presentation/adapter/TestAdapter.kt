package com.phucpt.mymovie.presentation.adapter

import androidx.viewbinding.ViewBinding
import com.phucpt.mymovie.codebase.BaseAdapter
import com.phucpt.mymovie.codebase.BaseItem
import com.phucpt.mymovie.databinding.ItemMovieBinding
import com.phucpt.mymovie.databinding.ItemTvSeriesBinding
import com.phucpt.mymovie.presentation.model.MovieUiModel
import com.phucpt.mymovie.presentation.model.TvSeries
import javax.inject.Inject

/**
 * Created by Phucpt on 10/07/2023 at 23:10
 */

class TestAdapter @Inject constructor() :
    BaseAdapter<BaseItem<*>, BaseAdapter.BaseViewHolder<*>>() {
    override fun getViewHolder(binding: ViewBinding): BaseViewHolder<*> {
        return when (binding) {
            is ItemMovieBinding -> ViewHolder(binding)
            is ItemTvSeriesBinding -> TvSeriesViewHolder(binding)
            else -> throw Exception("${binding.javaClass.simpleName} not supported")
        }
    }

    inner class ViewHolder(binding: ItemMovieBinding) :
        BaseViewHolder<ItemMovieBinding>(binding) {
        override fun bindViews(position: Int) {
            binding.txtTitle.text = (getItem(position) as MovieUiModel).title
        }
    }

    inner class TvSeriesViewHolder(binding: ItemTvSeriesBinding) :
        BaseViewHolder<ItemTvSeriesBinding>(binding) {
        override fun bindViews(position: Int) {
            binding.txtTitle.text = (getItem(position) as TvSeries).title
        }
    }
}