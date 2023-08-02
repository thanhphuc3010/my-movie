package com.phucpt.mymovie.presentation.model

import com.phucpt.mymovie.R
import com.phucpt.mymovie.codebase.BaseItem
import com.phucpt.mymovie.databinding.ItemMovieBinding

/**
 * Created by Phucpt on 10/07/2023 at 23:10
 */

data class Movie(
    val title: String
) : BaseItem<ItemMovieBinding> {
    override val layoutId: Int = R.layout.item_movie
    override val uniqueId: Any = title
}
