package com.phucpt.mymovie

import android.os.Bundle
import com.phucpt.mymovie.codebase.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeComponents() {
        val name = "Pham Thanh Phuc"
        val firstName = name.substring(0, 7)
    }

    override fun initializeEvents() {

    }

    override fun initializeObservables() {

    }

    override fun initializeData() {}
}