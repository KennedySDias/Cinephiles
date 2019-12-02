package com.arctouch.codechallenge.ui.upcomingMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.FragmentUpcomingMoviesBinding
import com.arctouch.codechallenge.extensions.observe
import com.arctouch.codechallenge.ui.base.BaseFragment
import com.example.domain.model.ShortMovieData
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpcomingMoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentUpcomingMoviesBinding
    private var upcomingMoviesAdapter: UpcomingMoviesAdapter? = null
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

        if (upcomingMoviesAdapter == null) upcomingMoviesAdapter = UpcomingMoviesAdapter(viewModel)

        configureComponents()
        configureObservables()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init()
    }

    private fun configureComponents() {
        configureList()
    }

    private fun configureObservables() {
        observe(viewModel.notConnectedOb, ::handleNotConnected)
        observe(viewModel.gettingDataOb, ::handleGettingData)
        observe(viewModel.fatalErrorOb, ::handleFatalError)
        observe(viewModel.moviesOb, ::handleMovies)
        observe(viewModel.errorOb, ::handleError)
    }

    private fun handleMovies(liveData: LiveData<PagedList<ShortMovieData>>) {
        liveData.observe(this, Observer {
            upcomingMoviesAdapter?.submitList(it)
        })
    }

    private fun configureList() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUpcomingMovies(true)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = upcomingMoviesAdapter
        }
    }

    private fun handleNotConnected(notConnected: Boolean) {
        if (notConnected) {
            showNoConnectionDialog {
                viewModel.init()
            }
        } else {
            hideNotConnectedDialog()
        }
    }

    private fun handleFatalError(message: String) {
        showToast(message)
        finish()
    }

    private fun handleError(message: String) {
        showSnackbar(message)
    }

    private fun handleGettingData(loading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = loading
    }

    companion object {
        fun newInstance() = UpcomingMoviesFragment()
    }

}