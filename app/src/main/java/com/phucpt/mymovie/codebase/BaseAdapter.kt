package com.phucpt.mymovie.codebase

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * Created by Phucpt on 09/07/2023 at 17:59
 */

abstract class BaseAdapter<T : BaseItem<*>, VH : BaseAdapter.BaseViewHolder<*>> :
    ListAdapter<BaseItem<*>, VH>(
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<BaseItem<*>>() {
            override fun areItemsTheSame(oldItem: BaseItem<*>, newItem: BaseItem<*>): Boolean {
                return oldItem.uniqueId == newItem.uniqueId
            }

            override fun areContentsTheSame(oldItem: BaseItem<*>, newItem: BaseItem<*>): Boolean {
                return oldItem == newItem
            }
        }).build()
    ) {
    private var lastItemForViewTypeLookup: BaseItem<*>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val item = getItemForViewType(viewType)
        val binding = item.initializeViewBinding(parent)
        return getViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindViews(position)
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        lastItemForViewTypeLookup = item
        return item.layoutId
    }

    abstract fun getViewHolder(binding: ViewBinding): VH

    /**
     * This idea was copied from Epoxy. :wave: Bright idea guys!
     *
     * Find the model that has the given view type so we can create a viewholder for that model.
     *
     * To make this efficient, we rely on the RecyclerView implementation detail that [ListAdapter.getItemViewType]
     * is called immediately before [ListAdapter.onCreateViewHolder]. We cache the last model
     * that had its view type looked up, and unless that implementation changes we expect to have a
     * very fast lookup for the correct model.
     *
     *
     * To be safe, we fallback to searching through all models for a view type match. This is slow and
     * shouldn't be needed, but is a guard against RecyclerView behavior changing.
     */
    private fun getItemForViewType(viewType: Int): BaseItem<*> {
        val lastItemForViewTypeLookup = lastItemForViewTypeLookup
        if (lastItemForViewTypeLookup != null
            && lastItemForViewTypeLookup.layoutId == viewType
        ) {
            // We expect this to be a hit 100% of the time
            return lastItemForViewTypeLookup
        }

        // To be extra safe in case RecyclerView implementation details change...
        for (i in 0 until itemCount) {
            val item: BaseItem<*> = getItem(i)
            if (item.layoutId == viewType) {
                return item
            }
        }
        throw IllegalStateException("Could not find model for view type: $viewType")
    }

    abstract class BaseViewHolder<VB : ViewBinding>(protected val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bindViews(position: Int)
    }
}