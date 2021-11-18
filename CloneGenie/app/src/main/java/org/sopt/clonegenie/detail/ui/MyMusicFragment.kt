package org.sopt.clonegenie.detail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.clonegenie.R
import org.sopt.clonegenie.databinding.FragmentMyMusicBinding
import org.sopt.clonegenie.detail.adapter.MyMusicRecyclerviewAdapter
import org.sopt.clonegenie.detail.data.MyMusicData
import org.sopt.clonegenie.util.DetailRecyclerViewItemDecoration


class MyMusicFragment : Fragment() {

    private var _binding: FragmentMyMusicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_music, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    @SuppressLint("ResourceType")
    private fun initAdapter() {
        val playList = mutableListOf(
            MyMusicData(R.drawable.img_detail_gracie_abrams2, "2021.11.09", 5),
            MyMusicData(R.drawable.img_detail_seventeen, "국힙", 23),
            MyMusicData(R.drawable.img_my_music_untitled, "2021.11.28", 50),
            MyMusicData(R.drawable.img_detail_mina, "신나는 노래들", 5),
            MyMusicData(R.drawable.img_detail_exo, "과제할 때 듣는 노래", 52),
            MyMusicData(R.drawable.img_detail_gracie_abrams2, "2021.11.09", 5),
            MyMusicData(R.drawable.img_detail_seventeen, "국힙", 23),
            MyMusicData(R.drawable.img_my_music_untitled, "2021.11.28", 50),
            MyMusicData(R.drawable.img_detail_mina, "신나는 노래들", 5),
            MyMusicData(R.drawable.img_detail_exo, "과제할 때 듣는 노래", 52)
        )

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}