package com.phucpt.mymovie.presentation.model

import com.phucpt.mymovie.R
import com.phucpt.mymovie.codebase.BaseItem
import com.phucpt.mymovie.databinding.ItemTvSeriesBinding

/**
 * Created by Phucpt on 10/07/2023 at 23:34
 */

data class TvSeries(val title: String) : BaseItem<ItemTvSeriesBinding> {
    override val layoutId = R.layout.item_tv_series
    override val uniqueId: Any = title
}
