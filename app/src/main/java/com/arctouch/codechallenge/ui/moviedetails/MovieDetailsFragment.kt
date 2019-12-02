package com.arctouch.codechallenge.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.FragmentMovieDetailsBinding
import com.arctouch.codechallenge.extensions.observe
import com.arctouch.codechallenge.ui.base.BaseFragment
import com.example.domain.model.FullMovieData
import com.example.domain.model.ShortMovieData
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment() {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by viewModel<MovieDetailsViewModel>()

    override fun onBackPressed(): Boolean {
        finish()
        return super.onBackPressed()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        binding.item = arguments?.getParcelable(EXTRA_MOVIE)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val view = binding.root

        configureObservables()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(arguments)
    }

    private fun configureObservables() {
        observe(viewModel.fullMovieOb, ::handleFullMovie)
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.errorOb, ::handleError)
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
    }

    private fun handleFatalError(error: String) {
        showToast(error)
        finish()
    }

    private fun handleError(error: String) {
        showSnackbar(error)
    }

    private fun handleFullMovie(fullMovieData: FullMovieData) {
        binding.textViewGenres.text = viewModel.getGenresNames(fullMovieData)
    }

    private fun handleGettingData(loading: Boolean) {
        if (loading) {
            binding.progressBarHorizontal.visibility = View.VISIBLE
        } else {
            binding.progressBarHorizontal.visibility = View.GONE
        }
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog { viewModel.getFullMovie() }
        } else {
            hideNotConnectedDialog()
        }
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"

        fun newInstance(movie: ShortMovieData?): MovieDetailsFragment {
            val args = Bundle()
            args.putParcelable(EXTRA_MOVIE, movie)

            val fragment = MovieDetailsFragment()
            fragment.arguments = args

            return fragment
        }
    }

}