package com.phucpt.mymovie.presentation.ui

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.phucpt.mymovie.R
import com.phucpt.mymovie.codebase.BaseFragment
import com.phucpt.mymovie.databinding.FragmentHomeBinding
import com.phucpt.mymovie.presentation.adapter.MovieAdapter
import com.phucpt.mymovie.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Phucpt on 06/07/2023 at 20:42
 */

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    override val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun initializeComponents() {
        binding.lstMovies.run {
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
            adapter = movieAdapter
        }
    }

    override fun initializeEvents() {
        movieAdapter.setOnItemClickListener {
//            val navOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.homeFragment, inclusive = true)
//                .build()
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, null)
//            MyDialog.show(childFragmentManager)
        }
    }

    override fun initializeObservables() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    movieAdapter.submitData(it)
                }
            }
            Log.d(javaClass.simpleName, "uiState collect()")
        }
    }

    override fun initializeData() {
        viewModel.fetchDiscoverMovies()
    }
}