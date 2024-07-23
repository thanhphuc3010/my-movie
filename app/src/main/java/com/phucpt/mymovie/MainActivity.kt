package com.phucpt.mymovie

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.phucpt.mymovie.codebase.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
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