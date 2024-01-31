package com.phucpt.mymovie.presentation.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import com.phucpt.core.domain.model.AcquirerDto
import com.phucpt.core.domain.model.ProfileDto
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
    companion object {
        const val TAG = "HomeFragment"
    }

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
        val profileJsonStr = requireContext().assets.open("profile.json")
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        val acquirersMap = gson.fromJson(profileJsonStr, ProfileDto::class.java).acquirersMap
        val acquirers = acquirersMap
            .filterKeys { it.startsWith("Acquirers[") }
            .map { entry -> gson.fromJson(gson.toJson(entry.value), AcquirerDto::class.java) }
            .map(AcquirerDto::toModel)

        acquirers.filter { it.tid.isNotBlank() && it.mid.isNotBlank() }
            .forEach {
//                Log.i(TAG, "acquirer: tid = ${it.tid}; mid = ${it.mid}")
                Log.i(TAG, "acquirer: $it")
            }
    }
}