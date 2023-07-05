package com.phucpt.mymovie.codebase

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Created by Phucpt on 05/07/2023 at 10:31
 */

typealias FragmentInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<out VB : ViewBinding, out VM : ViewModel>(
    private val inflate: FragmentInflate<VB>
) : Fragment(), BaseView {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract val viewModel: VM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(javaClass.simpleName, "onAttach()...")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(javaClass.simpleName, "onCreate()...")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(javaClass.simpleName, "onCreateView()...")
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(javaClass.simpleName, "onViewCreated()...")

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(javaClass.simpleName, "onDestroyView()...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(javaClass.simpleName, "onDestroy()...")
    }
}