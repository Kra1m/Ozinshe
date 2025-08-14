package com.example.ozinshe.presentation.Onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ozinshe.data.OnboardingModel
import com.example.ozinshe.databinding.FragmentOnboardingBinding
import com.example.ozinshe.databinding.ItemViewpagerOnboardingBinding

class OnboardingAdapter(): RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val onboardingModelList = mutableListOf<OnboardingModel>()

    fun submitList(list: List<OnboardingModel>){
        onboardingModelList.addAll(list)
        notifyDataSetChanged()
    }

    inner class OnboardingViewHolder(private val binding: ItemViewpagerOnboardingBinding):
        RecyclerView.ViewHolder(binding.root)  {
        fun bindItem(item: OnboardingModel){
            binding.tvTitleOnboarding.text = item.title
            binding.tvDescOnboarding.text = item.description
            binding.imgTvOnboarding.setImageResource(item.imageId)
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnboardingAdapter.OnboardingViewHolder {
        return OnboardingViewHolder(ItemViewpagerOnboardingBinding.inflate
            (LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: OnboardingAdapter.OnboardingViewHolder,
        position: Int
    ) {
        holder.bindItem(onboardingModelList[position])
    }

    override fun getItemCount(): Int {
        return onboardingModelList.size
    }


}