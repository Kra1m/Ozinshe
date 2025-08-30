package com.example.ozinshe.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentProfileBinding
import com.example.ozinshe.provideNavigationHost

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        provideNavigationHost()?.apply {
            setNavigationVisibility(true)
        }


    }

}