package com.rkukac.movieapp.ui.navbar.common

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import com.rkukac.movieapp.ui.navbar.adapter.MovieAdapter
import com.rkukac.movieapp.ui.navbar.adapter.MovieLoaderAdapter
import com.rkukac.movieapp.ui.navbar.model.UiMovie
import com.rkukac.movieapp.util.paging.common.MovieListPagingKey
import com.rkukac.movieapp.util.paging.common.MovieListPagingSourceData
import com.rkukac.movieapp.util.paging.isRefreshError
import com.rkukac.movieapp.util.paging.refreshOrAppendLoading
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

abstract class MovieListFragment<VS : Any, PSD : MovieListPagingSourceData, PK : MovieListPagingKey, VM : MovieListViewModel<VS, PSD, PK>> :
    RainbowCakeFragment<VS, VM>() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var movieLoaderAdapter: MovieLoaderAdapter

    private val loadStateListener: (CombinedLoadStates) -> Unit = { loadStates ->
        if (loadStates.isRefreshError) {
            viewModel.loadingStateError()
        } else {
            loadStates.setupMovieLoaderAdapter()
        }
    }

    private var pagingJob: Job? = null

    abstract val recyclerView: RecyclerView

    @CallSuper
    override fun onEvent(event: OneShotEvent) {
        when (event) {
            is MovieListViewModel.OpenMovieDetailsEvent -> openMovieDetails(event.id)
            is MovieListViewModel.CollectMoviesEvent -> collectMovies(event)
            is MovieListViewModel.CancelMoviesCollectEvent -> cancelCollection()
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupMovieAdapter()
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupRecyclerView()
        setupLoadStateListener()
    }

    @CallSuper
    override fun onStop() {
        releaseAdapters()
        super.onStop()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = ConcatAdapter(movieAdapter, movieLoaderAdapter)
    }

    //region Adapter
    private fun setupMovieAdapter() {
        movieAdapter.setItemClickListener {
            viewModel.movieItemSelected(movie = it)
        }
    }

    private fun releaseAdapters() {
        movieAdapter.removeLoadStateListener(loadStateListener)
        recyclerView.adapter = null
    }
    //endregion

    //region Load States
    private fun setupLoadStateListener() {
        movieAdapter.addLoadStateListener(loadStateListener)
    }

    private fun CombinedLoadStates.setupMovieLoaderAdapter() = with(movieLoaderAdapter) {
        loadState = refreshOrAppendLoading
    }
    //endregion

    //region Collect
    private fun cancelCollection() {
        pagingJob?.cancel()
        renderMovies(PagingData.empty())
    }

    private fun collectMovies(event: MovieListViewModel.CollectMoviesEvent) {
        pagingJob = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            event.pager.flow.cachedIn(viewModel.viewModelScope).collectLatest {
                renderMovies(it)
            }
        }
    }
    //endregion

    //region Render
    private fun renderMovies(movies: PagingData<UiMovie>) {
        movieAdapter.submitData(viewLifecycleOwner.lifecycle, movies)
    }
    //endregion

    abstract fun openMovieDetails(id: Int)
}