package com.phucpt.mymovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.phucpt.core.domain.model.Movie
import com.phucpt.core.domain.repository.DiscoverRepository
import com.phucpt.mymovie.codebase.BaseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Phucpt on 06/07/2023 at 20:42
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val uiState: StateFlow<PagingData<Movie>> = _uiState


    val movies = MutableStateFlow<List<BaseItem<*>>>(emptyList())

    fun fetchDiscoverMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            discoverRepository.getMoviesStream().cachedIn(viewModelScope)
                .collectLatest {
                    _uiState.value = it
                }
        }
    }
}