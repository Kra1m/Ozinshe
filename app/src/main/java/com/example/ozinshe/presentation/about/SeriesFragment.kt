package com.example.ozinshe.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentSeriesBinding

class SeriesFragment : Fragment() {

    private lateinit var binding: FragmentSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}