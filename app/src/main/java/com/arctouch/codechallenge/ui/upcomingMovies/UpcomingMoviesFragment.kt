package com.arctouch.codechallenge.ui.upcomingMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.FragmentUpcomingMoviesBinding
import com.arctouch.codechallenge.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingMoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentUpcomingMoviesBinding
    private val viewModel by viewModel<UpcomingMoviesViewModel>()

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
                DataBindingUtil.inflate(inflater, R.layout.fragment_upcoming_movies, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
    }

    companion object {
        fun newInstance() = UpcomingMoviesFragment()
    }
}