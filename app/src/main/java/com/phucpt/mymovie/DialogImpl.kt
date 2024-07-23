package com.phucpt.mymovie

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.phucpt.mymovie.databinding.DialogCommonBinding

/**
 * Created on 05/06/2024 at 19:25
 *
 * @author phucpt
 */

class DialogImpl : DialogFragment() {
    companion object {
        private const val TAG = "DialogImpl"
    }

    // ViewBindings
    private var _binding: DialogCommonBinding? = null
    private val binding: DialogCommonBinding
        get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogCommonBinding.inflate(layoutInflater, null, false)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()

        dialog.window?.setBackgroundDrawableResource(R.drawable.shape_color_ffffff_corner_16)
        return dialog
    }

    fun show(manager: FragmentManager) {
        if (!isAdded) {
            show(manager, null)
        }
    }

}