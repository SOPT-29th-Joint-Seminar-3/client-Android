package org.sopt.clonegenie.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.clonegenie.databinding.FragmentDialogBinding
import org.sopt.clonegenie.detail.remote.PlayListServiceCreator

class CustomDialog(context: Context) : DialogFragment() {
    private var _binding: FragmentDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.btnCreate.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.d(
                        "성공", PlayListServiceCreator.myMusicPlayListService.createPlayList(
                            binding.etTitle.text.toString(),
                            binding.etDescription.text.toString()
                        ).data.title
                    )

                } catch (e: Exception) {
                    Log.d("실패", e.message!!)
                }
            }
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}