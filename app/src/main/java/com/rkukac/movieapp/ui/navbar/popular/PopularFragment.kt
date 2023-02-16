package com.rkukac.movieapp.ui.navbar.popular

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentPopularBinding
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.ui.navbar.common.MovieListFragment
import com.rkukac.movieapp.ui.navbar.popular.PopularFragment.ViewAnimator.CONTENT
import com.rkukac.movieapp.ui.navbar.popular.PopularFragment.ViewAnimator.EMPTY
import com.rkukac.movieapp.ui.navbar.popular.PopularFragment.ViewAnimator.ERROR
import com.rkukac.movieapp.ui.navbar.popular.PopularFragment.ViewAnimator.toChildPosition
import com.rkukac.movieapp.util.paging.PopularPagingKey
import com.rkukac.movieapp.util.ui.displayedChildIfDifferent
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment :
    MovieListFragment<PopularViewState, PopularPagingSourceData, PopularPagingKey, PopularViewModel>() {

    private val binding by viewBinding(FragmentPopularBinding::bind)

    override val recyclerView: RecyclerView
        get() = binding.incList.recyclerView

    private object ViewAnimator {

        const val CONTENT = 0
        private const val LOADING = 1
        const val ERROR = 2
        const val EMPTY = 3

        fun PopularViewState.toChildPosition(): Int {
            return when {
                loading -> LOADING
                error -> ERROR
                empty -> EMPTY
                else -> CONTENT
            }
        }
    }

    override fun getViewResource() = R.layout.fragment_popular

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: PopularViewState) {
        val position = viewState.toChildPosition()
        when (position) {
            CONTENT -> renderContent()
            ERROR -> renderError(viewState.errorModel)
            EMPTY -> renderEmpty(viewState.emptyModel)
        }

        renderViewAnimator(position)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupErrorButton()
        viewModel.loadInitialData()
    }

    private fun setupErrorButton() {
        binding.incError.buttonError.setOnClickListener { viewModel.errorButtonClick() }
    }

    //region Render
    private fun renderViewAnimator(childPosition: Int) = with(binding) {
        viewAnimator.displayedChildIfDifferent = childPosition
    }

    private fun renderContent() {
        binding.incList.recyclerView.isVisible = true
    }

    private fun renderError(errorModel: UiStateModel) = with(binding.incError) {
        textErrorTitle.text = errorModel.title
        textErrorDescription.text = errorModel.description
        buttonError.text = errorModel.buttonText
    }

    private fun renderEmpty(emptyModel: UiStateModel) = with(binding.incEmpty) {
        textEmptyTitle.text = emptyModel.title
        textEmptyDescription.text = emptyModel.description
        buttonEmpty.isVisible = emptyModel.buttonText.isNotEmpty()
    }
    //endregion

    override fun openMovieDetails(id: Int) {
        TODO("Not yet implemented")
    }
}