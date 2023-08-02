package com.phucpt.mymovie

import android.os.Bundle
import com.phucpt.mymovie.codebase.BaseActivity
import com.phucpt.mymovie.data.datasource.remote.api.TheMovieDBApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    @Inject
    lateinit var theMovieDBApi: TheMovieDBApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeComponents() {

    }

    override fun initializeEvents() {

    }

    override fun initializeObservables() {

    }

    override fun initializeData() {}
}