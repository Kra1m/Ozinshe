package com.example.ozinshe.presentation.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private lateinit var binding : FragmentImageBinding
    private val args: ImageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(args.imageLink)
            .into(binding.imageViewScreenshot)

//        val onBackPressedCallback = object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                 findNavController().popBackStack()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
//            onBackPressedCallback)

        binding.imageViewScreenshot.maximumScale = 10f

        binding.btnBackScreenshot.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }


}