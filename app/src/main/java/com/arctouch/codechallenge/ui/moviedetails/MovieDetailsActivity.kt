package com.arctouch.codechallenge.ui.moviedetails

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.extensions.addFragment
import com.arctouch.codechallenge.ui.base.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    override var containerId: Int = R.id.frameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        openFragment()
    }

    private fun openFragment() {
        val fragment =
                MovieDetailsFragment.newInstance(
                        intent.getParcelableExtra(MovieDetailsFragment.EXTRA_MOVIE)
                )
        supportFragmentManager.addFragment(containerId, fragment)
    }

}