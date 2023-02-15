package com.rkukac.movieapp.ui.details

import co.zsmb.rainbowcake.withIOContext
import com.rkukac.movieapp.domain.MovieInteractor
import com.rkukac.movieapp.ui.details.model.UiMovieDetails
import com.rkukac.movieapp.ui.details.model.toUiMovieDetails
import com.rkukac.movieapp.ui.model.UiStateModel
import com.rkukac.movieapp.ui.model.toUiStateModel
import com.rkukac.movieapp.util.MovieHelper
import java.math.BigDecimal
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val interactor: MovieInteractor,
    private val movieHelper: MovieHelper
) {

    private val imageFormatterBlock: (String?) -> String? = { image ->
        movieHelper.getFormattedImage(image = image)
    }

    private val amountFormatterBlock: (BigDecimal?) -> String = { amount ->
        movieHelper.getFormattedAmount(amount = amount)
    }

    fun getDetailsErrorStateModel(): UiStateModel =
        interactor.getDetailsErrorStateModel().toUiStateModel()

    suspend fun getMovieDetails(id: Int): UiMovieDetails = withIOContext {
        interactor.getMovieDetails(movieId = id).toUiMovieDetails(
            imageFormatterBlock = imageFormatterBlock,
            amountFormatterBlock = amountFormatterBlock
        )
    }
}