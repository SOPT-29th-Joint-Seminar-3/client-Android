package org.sopt.clonegenie.detail.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.sopt.clonegenie.R
import org.sopt.clonegenie.databinding.FragmentDetailBinding
import org.sopt.clonegenie.detail.adapter.DetailRecyclerViewAdapter
import org.sopt.clonegenie.detail.data.DetailData
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
        initAdapter()
    }

    private fun initAdapter() {
        val musicList = mutableListOf(
            DetailData(R.drawable.img_detail_gracie_abrams, "21", "Gracie Abrams"),
            DetailData(R.drawable.img_detail_seventeen, "21", "Gracie Abrams"),
            DetailData(R.drawable.img_detail_mina, "tlssksmsthd", "미나와 채원"),
            DetailData(R.drawable.img_detail_gracie_abrams2, "신나는 송", "Gracie Abrams"),
            DetailData(R.drawable.img_detail_exo, "으르렁", "엑소")
        )

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