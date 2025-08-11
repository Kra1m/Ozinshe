package com.example.ozinshe.presentation.Onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ozinshe.data.OnboardingInfoList
import com.example.ozinshe.databinding.FragmentOnboardingBinding
import com.example.ozinshe.R

class OnboardingFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = OnboardingAdapter()
        adapter.submitList(OnboardingInfoList.onboardingModelList)
        binding.viewPager2OnboardingFragment.adapter = adapter

        val viewPagerCallback = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == OnboardingInfoList.onboardingModelList.size - 1){
                    binding.btnSkipOnboarding.visibility = View.INVISIBLE
                    binding.btnNextOnboarding.visibility = View.VISIBLE
                }else{
                    binding.btnSkipOnboarding.visibility = View.VISIBLE
                    binding.btnNextOnboarding.visibility = View.INVISIBLE
                }
            }
        }
        binding.dotsIndicator.setViewPager2(binding.viewPager2OnboardingFragment)

        binding.viewPager2OnboardingFragment.registerOnPageChangeCallback(viewPagerCallback)

        binding.btnSkipOnboarding.setOnClickListener {
            binding.viewPager2OnboardingFragment.currentItem = OnboardingInfoList.onboardingModelList.size - 1
        }

        binding.btnNextOnboarding.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }



    }

}