package com.arctouch.codechallenge.ui.main

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.extensions.addFragment
import com.arctouch.codechallenge.ui.base.BaseActivity
import com.arctouch.codechallenge.ui.upcomingMovies.UpcomingMoviesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureFragment()
    }

    private fun configureFragment() {
        supportFragmentManager.addFragment(R.id.frameLayoutList, UpcomingMoviesFragment.newInstance())
    }

}
