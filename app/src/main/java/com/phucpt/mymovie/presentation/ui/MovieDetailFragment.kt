package com.phucpt.mymovie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.phucpt.mymovie.codebase.BaseFragment
import com.phucpt.mymovie.databinding.FragmentMovieDetailBinding

/**
 * Created by Phucpt on 16/07/2023 at 15:40
 */

class MovieDetailFragment :
    BaseFragment<FragmentMovieDetailBinding, ViewModel>(FragmentMovieDetailBinding::inflate) {
    override val viewModel: ViewModel by viewModels<ViewModel>()

    override fun initializeComponents() {

    }

    override fun initializeEvents() {

    }

    override fun initializeObservables() {

    }

    override fun initializeData() {

    }
}