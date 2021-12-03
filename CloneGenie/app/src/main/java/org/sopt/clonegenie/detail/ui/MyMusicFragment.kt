package org.sopt.clonegenie.detail.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.*
import org.sopt.clonegenie.R
import org.sopt.clonegenie.databinding.FragmentMyMusicBinding
import org.sopt.clonegenie.detail.adapter.MyMusicRecyclerviewAdapter
import org.sopt.clonegenie.detail.data.MyMusicData
import org.sopt.clonegenie.detail.remote.PlayListServiceCreator
import org.sopt.clonegenie.util.DetailRecyclerViewItemDecoration
import kotlin.properties.Delegates


class MyMusicFragment : Fragment() {

    private var _binding: FragmentMyMusicBinding? = null
    private val binding get() = _binding!!
    var upMenuData = mutableListOf<MutableLiveData<String>>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_music, container, false)
        CoroutineScope(Dispatchers.Main).launch {
            initNetwork().await()
            binding.getData = this@MyMusicFragment
//            Log.d("아니 왜진짜","${upMenuData[0].value}")
            upMenuData.add(MutableLiveData<String>().apply { value = "시1발이게맞냐고" })
            Handler().postDelayed({
                upMenuData[0].value = "바뀌어라"
            },4000L)
        }
        binding.lifecycleOwner = viewLifecycleOwner
//        upMenuData.add(MutableLiveData<String>().apply { value = "시1발이게맞냐고" })
//        Handler().postDelayed({
//            upMenuData[0].value = "바뀌어라"
//        },4000L)
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


    private suspend fun initNetwork() : Deferred<String> =
        CoroutineScope(Dispatchers.IO).async {
            val response = PlayListServiceCreator.myMusicPlayListService.getMyMusicPlayList()
            val data = response.body()?.data
            if (data != null) {
                Log.d("하왜이러는겨","${data.likeCount}")
            }
            withContext(Dispatchers.Main) {
                if (data != null) {
                    upMenuData.add(MutableLiveData<String>().apply {
                        value = data.likeCount.toString()
                    })
                    upMenuData.add(MutableLiveData<String>().apply {
                        value = data.saveCount.toString()
                    })
                    upMenuData.add(MutableLiveData<String>().apply {
                        value = data.recentPlayedCount.toString()
                    })
                    upMenuData.add(MutableLiveData<String>().apply {
                        value = data.mostPlayedCount.toString()
                    })
                }
            }
            return@async "Finished"
        }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}