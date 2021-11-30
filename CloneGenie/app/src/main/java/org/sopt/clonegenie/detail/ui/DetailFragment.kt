package org.sopt.clonegenie.detail.ui

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
import org.sopt.clonegenie.detail.remote.DetailPlayListServiceCreator
import org.sopt.clonegenie.util.DetailRecyclerViewItemDecoration

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNetwork()
    }

    private fun initNetwork() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val list = DetailPlayListServiceCreator.detailService.getDetailPlayList("1")
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}