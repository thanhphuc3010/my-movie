package com.phucpt.mymovie.codebase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Phucpt on 05/07/2023 at 10:52
 */

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName, "onCreate()...")

        initializeComponents()
        initializeEvents()
        initializeObservables()
        initializeData()
    }

    override fun onStart() {
        super.onStart()
        Log.d(javaClass.simpleName, "onStart()...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(javaClass.simpleName, "onResume()...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(javaClass.simpleName, "onPause()...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(javaClass.simpleName, "onStop()...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(javaClass.simpleName, "onRestart()...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(javaClass.simpleName, "onDestroy()...")
    }
}