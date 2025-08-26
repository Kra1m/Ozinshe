package com.example.ozinshe.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentSeriesBinding
import com.example.ozinshe.provideNavigationHost

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModels: SeriesViewModel by viewModels()
    private val args: SeriesFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }

        binding.toolbarSeriesIncluded.tvTittleForToolBar.text = "Бөлімдер"
        binding.toolbarSeriesIncluded.btnBackToolBar.setOnClickListener {
            findNavController().popBackStack()
        }

        val token = SharedProvider(requireContext()).getToken()
        viewModels.getSeriesById(token, args.movieId)

        val adapterSeries = SeriesAdapter()
        binding.rcViewSeriesOfSeason.adapter = adapterSeries

        viewModels.videosResponse.observe(viewLifecycleOwner){
            adapterSeries.submitList(it[0].videos)
        }

        binding.rcViewSeriesOfSeason.addItemDecoration(
            CustomDividerItemDecoration(
                getDrawable(requireContext(), R.drawable.dividor_1dp_grey)!!
            )
        )

        adapterSeries.setOnVideoClickListener(object : RcViewItemClickVideoCallBack{
            override fun onVideoItemClick(videoLink: String) {
                val action = SeriesFragmentDirections.actionSeriesFragmentToVideoFragment(videoLink)
                findNavController().navigate(action)
            }
        })

    }


}