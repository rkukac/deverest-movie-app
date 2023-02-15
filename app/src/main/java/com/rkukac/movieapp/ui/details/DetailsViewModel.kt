package com.rkukac.movieapp.ui.details

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val presenter: DetailsPresenter
) : RainbowCakeViewModel<DetailsViewState>(DetailsViewState()) {

    fun loadDetails(id: Int) {
        viewState = viewState.copy(
            id = id,
            errorModel = presenter.getDetailsErrorStateModel()
        )

        loadDetails()
    }

    private fun loadDetails() = execute {
        viewState = viewState.toLoadingState()

        viewState = try {
            viewState.toContentState(
                details = presenter.getMovieDetails(id = viewState.id)
            )
        } catch (e: Exception) {
            Timber.d(e, "Failed to load movie details!")
            viewState.toErrorState()
        }
    }

    fun errorButtonClick() {
        loadDetails()
    }
}