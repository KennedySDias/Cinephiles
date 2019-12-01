package com.arctouch.codechallenge.ui.main

import android.os.Bundle
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureFragment()
    }

    private fun configureFragment() {
        // TODO
    }

}
