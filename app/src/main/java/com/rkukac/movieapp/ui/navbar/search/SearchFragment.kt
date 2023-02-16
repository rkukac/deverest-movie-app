package com.rkukac.movieapp.ui.navbar.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.google.android.material.snackbar.Snackbar
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentSearchBinding
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.ui.navbar.common.MovieListFragment
import com.rkukac.movieapp.ui.navbar.search.SearchFragment.ViewAnimator.CONTENT
import com.rkukac.movieapp.ui.navbar.search.SearchFragment.ViewAnimator.EMPTY
import com.rkukac.movieapp.ui.navbar.search.SearchFragment.ViewAnimator.ERROR
import com.rkukac.movieapp.ui.navbar.search.SearchFragment.ViewAnimator.toChildPosition
import com.rkukac.movieapp.util.navigation.navigateSafe
import com.rkukac.movieapp.util.paging.SearchPagingKey
import com.rkukac.movieapp.util.ui.displayedChildIfDifferent
import com.rkukac.movieapp.util.ui.hideKeyboard
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment :
    MovieListFragment<SearchViewState, SearchPagingSourceData, SearchPagingKey, SearchViewModel>() {

    private val binding by viewBinding(FragmentSearchBinding::bind)

    override val recyclerView: RecyclerView
        get() = binding.incList.recyclerView

    private object ViewAnimator {

        const val CONTENT = 0
        private const val LOADING = 1
        const val ERROR = 2
        const val EMPTY = 3

        fun SearchViewState.toChildPosition(): Int {
            return when {
                loading -> LOADING
                error -> ERROR
                empty -> EMPTY
                else -> CONTENT
            }
        }
    }

    override fun getViewResource() = R.layout.fragment_search

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: SearchViewState) {
        val position = viewState.toChildPosition()
        when (position) {
            CONTENT -> renderContent()
            ERROR -> renderError(viewState.errorModel)
            EMPTY -> renderEmpty(viewState.emptyModel)
        }

        renderViewAnimator(position)
    }

    override fun onEvent(event: OneShotEvent) {
        when (event) {
            is SearchViewModel.ShowSnackMessage -> showSnackMessage(event.message)
            is SearchViewModel.HideKeyboardEvent -> hideKeyboard()
            else -> super.onEvent(event)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadInitialData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchButton()
        setupSearchEditText()
    }

    private fun setupSearchButton() {
        binding.buttonSearch.setOnClickListener { startSearch() }
    }

    private fun setupSearchEditText() {
        binding.editTextContentSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                startSearch()
            }
            return@setOnEditorActionListener false
        }
    }

    private fun startSearch() {
        viewModel.searchButtonClick(searchKeyword = binding.editTextContentSearch.text?.toString())
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
        buttonError.isVisible = errorModel.buttonText.isNotEmpty()
    }

    private fun renderEmpty(emptyModel: UiStateModel) = with(binding.incEmpty) {
        textEmptyTitle.text = emptyModel.title
        textEmptyDescription.text = emptyModel.description
        buttonEmpty.isVisible = emptyModel.buttonText.isNotEmpty()
    }
    //endregion

    private fun showSnackMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun openMovieDetails(id: Int) = navigateSafe {
        navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id = id))
    }

}