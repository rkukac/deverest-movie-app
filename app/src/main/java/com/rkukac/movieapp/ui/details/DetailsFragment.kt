package com.rkukac.movieapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentDetailsBinding
import com.rkukac.movieapp.ui.details.DetailsFragment.ViewAnimator.toChildPosition
import com.rkukac.movieapp.ui.details.model.UiMovieDetails
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.util.ui.displayedChildIfDifferent
import com.rkukac.movieapp.util.ui.loadImageUrl
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : RainbowCakeFragment<DetailsViewState, DetailsViewModel>() {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    private val args: DetailsFragmentArgs by navArgs()

    private object ViewAnimator {

        private const val LOADING = 0
        const val ERROR = 1
        const val CONTENT = 2

        fun DetailsViewState.toChildPosition(): Int {
            return when {
                loading -> LOADING
                error -> ERROR
                else -> CONTENT
            }
        }
    }

    override fun getViewResource() = R.layout.fragment_details

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: DetailsViewState) {
        val position = viewState.toChildPosition()
        when (position) {
            ViewAnimator.CONTENT -> renderContent(viewState.details)
            ViewAnimator.ERROR -> renderError(viewState.errorModel)
        }

        renderViewAnimator(position)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        viewModel.loadDetails(id = args.id)
    }

    private fun setupButton() {
        binding.incError.buttonError.setOnClickListener { viewModel.errorButtonClick() }
    }

    //region Render
    private fun renderViewAnimator(childPosition: Int) = with(binding) {
        viewAnimator.displayedChildIfDifferent = childPosition
    }

    private fun renderContent(details: UiMovieDetails?) = with(binding.incContent) {
        details?.let {
            textDetailsTitle.text = it.title
            imageDetails.loadImageUrl(it.image)
            textDetailsValueReleaseDate.text = it.releaseDate
            textDetailsValueRating.text = it.rating
            textDetailsValuePopularity.text = it.popularity
            textDetailsValueBudget.text = it.budget
            textDetailsValueRevenue.text = it.revenue
            textDetailsValueOverview.text = it.overview
        }
    }

    private fun renderError(errorModel: UiStateModel) = with(binding.incError) {
        textErrorTitle.text = errorModel.title
        textErrorDescription.text = errorModel.description
        buttonError.text = errorModel.buttonText
    }
    //endregion
}