package com.phucpt.mymovie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.phucpt.mymovie.databinding.FragmentMovieDetailBinding

/**
 * Created by Phucpt on 16/07/2023 at 16:14
 */

class MyDialog : DialogFragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(javaClass.simpleName, "onCreateView()...")
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun show(fragmentManager: FragmentManager) {
            MyDialog().show(fragmentManager, "Test")
        }
    }
}