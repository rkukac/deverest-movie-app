package com.rkukac.movieapp.ui.details

import androidx.navigation.fragment.navArgs
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.hilt.getViewModelFromFactory
import com.rkukac.movieapp.R
import com.rkukac.movieapp.databinding.FragmentDetailsBinding
import com.rkukac.movieapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : RainbowCakeFragment<DetailsViewState, DetailsViewModel>() {

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    private val args: DetailsFragmentArgs by navArgs()

    override fun getViewResource() = R.layout.fragment_details

    override fun provideViewModel() = getViewModelFromFactory()

    override fun render(viewState: DetailsViewState) = Unit
}