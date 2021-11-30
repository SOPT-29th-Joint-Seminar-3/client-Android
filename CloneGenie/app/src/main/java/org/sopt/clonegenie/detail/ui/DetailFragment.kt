package org.sopt.clonegenie.detail.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.clonegenie.databinding.FragmentDetailBinding
import org.sopt.clonegenie.detail.adapter.DetailRecyclerViewAdapter
import org.sopt.clonegenie.detail.data.Song
import org.sopt.clonegenie.detail.remote.PlayListServiceCreator
import org.sopt.clonegenie.util.DetailRecyclerViewItemDecoration

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    companion object {
        const val CHECK_STAR = "CHECK_STAR"
        const val CHECK_KEY = "CHECK_KEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStar()
        initNetwork()
        starClick()
    }

    private fun initNetwork() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val list = PlayListServiceCreator.detailService.getDetailPlayList("1")
                withContext(Dispatchers.Main) {
                    binding.tvTitle.text = list.data?.title
                    binding.tvExplain.text = list.data?.description
                    binding.tvCollectionSongCount.text = list.data?.total.toString() + "곡"
                    initAdapter(list.data!!.songs)
                }
            } catch (e: Exception) {
                Log.d("실패", e.message!!)
            }
        }
    }

    private fun initAdapter(musicList: MutableList<Song>) {
        val adpater = DetailRecyclerViewAdapter(musicList)
        binding.rvDetail.adapter = adpater
        binding.rvDetail.addItemDecoration(DetailRecyclerViewItemDecoration(14))
        binding.rvDetail.layoutManager = LinearLayoutManager(requireContext())
        adpater.submitList(musicList) // 아이템 업데이트
    }

    private fun starClick() {
        binding.ivStar.setOnClickListener {
            binding.ivStar.toggle()
            setStarState()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    PlayListServiceCreator.detailService.getDetailStart("1")
                } catch (e: Exception) {
                    Log.d("즐찾 실패", e.message!!)
                }
            }
        }
    }

    private fun setStarState() {
        val sharedPreference = requireActivity().getSharedPreferences(CHECK_KEY, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putBoolean(CHECK_STAR, binding.ivStar.isChecked)
        editor.apply()
    }

    private fun getStarState(): Boolean {
        val sharedPreference = requireActivity().getSharedPreferences(CHECK_KEY, Context.MODE_PRIVATE)
        return sharedPreference.getBoolean(CHECK_STAR, false)
    }

    private fun initStar() {
        if (getStarState()) {
            binding.ivStar.isChecked = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}