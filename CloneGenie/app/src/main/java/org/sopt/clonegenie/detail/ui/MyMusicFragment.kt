package org.sopt.clonegenie.detail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.sopt.clonegenie.R
import org.sopt.clonegenie.databinding.FragmentMyMusicBinding
import org.sopt.clonegenie.detail.adapter.MyMusicRecyclerviewAdapter
import org.sopt.clonegenie.detail.data.ResponseMyMusicPlayListData
import org.sopt.clonegenie.detail.remote.PlayListServiceCreator
import org.sopt.clonegenie.util.CustomDialog
import org.sopt.clonegenie.util.DetailRecyclerViewItemDecoration


class MyMusicFragment : Fragment() {

    private var _binding: FragmentMyMusicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_music, container, false)
        initNetwork()
        initCreatePlayList()
        return binding.root
    }

    @SuppressLint("ResourceType")
    private fun initAdapter(playList: List<ResponseMyMusicPlayListData.Likes>) {

        val adpater = MyMusicRecyclerviewAdapter(playList) { position ->
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcv_home_container, DetailFragment())
            transaction.commit()
        }
        binding.rvMyMusic.adapter = adpater
        binding.rvMyMusic.addItemDecoration(DetailRecyclerViewItemDecoration(14))
        binding.rvMyMusic.layoutManager = LinearLayoutManager(requireContext())
        adpater.submitList(playList) // 아이템 업데이트
        unfoldBtnListener(adpater)
    }

    private fun unfoldBtnListener(adapter: MyMusicRecyclerviewAdapter) {
        binding.layoutFold.setOnClickListener {
            if (adapter.loadStatus) {
                binding.ivUnfoldButton.setImageResource(R.drawable.ic_my_music_fold)
                binding.tvUnfoldButton.setText(R.string.my_music_fold)
                adapter.loadStatus = false
            } else {
                binding.ivUnfoldButton.setImageResource(R.drawable.ic_my_music_unfold)
                binding.tvUnfoldButton.setText(R.string.my_music_unfold)
                adapter.loadStatus = true
            }
            adapter.notifyDataSetChanged()
        }
    }


    private fun initNetwork() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = PlayListServiceCreator.myMusicPlayListService.getMyMusicPlayList()
                val data = response.body()?.data
                withContext(Dispatchers.Main) {
                    if (data != null) {
                        binding.tvMyMusicMyFavoriteCount.text = data.likeCount.toString()
                        binding.tvMyMusicMyStorageCount.text = data.saveCount.toString()
                        binding.tvMyMusicRecentPlayCount.text = data.recentPlayedCount.toString()
                        binding.tvMyMusicLotPlayedCount.text = data.mostPlayedCount.toString()
                        initAdapter(data.likes)
                    }
                }

            } catch (e: Exception) {
                Log.d("실패", e.message!!)
            }
        }
    }

    private fun initCreatePlayList() {
        binding.btnMakeNew.setOnClickListener {
            val dialog = CustomDialog(requireActivity())
            dialog.show(requireActivity().supportFragmentManager, "CustomDialog")

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}