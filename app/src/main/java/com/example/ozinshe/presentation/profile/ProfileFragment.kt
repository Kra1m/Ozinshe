package com.example.ozinshe.presentation.profile

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentProfileBinding
import com.example.ozinshe.provideNavigationHost
import kotlinx.coroutines.launch
import java.util.Locale

class ProfileFragment : Fragment(), OnLanguageSelectedListener {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

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

        systemLanguage()

        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        if(Build.VERSION.SDK_INT >= 26){
            transaction.setReorderingAllowed(false)
        }

        transaction.detach(this).attach(this).commit()

        viewModel.language.observe(viewLifecycleOwner){
            binding.textSelectedLanguage.text = it
        }


        binding.btnChangeLanguage.setOnClickListener {
            val bottomSheet = SelectedLanguageBottomSheet()
            bottomSheet.setOnLanguageSelectedListener(this)
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }

        binding.run {
            switchModeDayNight.isChecked = SharedProvider(requireContext()).getDayMode()
            switchModeDayNight.setOnCheckedChangeListener { _, isChecked ->
                SharedProvider(requireContext()).saveDayMode(isChecked)

                AppCompatDelegate.setDefaultNightMode(
                    if(isChecked) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }

    }

    override fun onLanguageSelected(language: String) {
        viewModel.selectLanguage(language)
    }

    private fun systemLanguage(){
        when(SharedProvider(requireContext()).getLanguage()){
            "kk" -> {
                val local = Locale("kk")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.textSelectedLanguage.text = "Қазақша"
            }
            "ru" -> {
                val local = Locale("ru")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.textSelectedLanguage.text = "Русский"
            }
            "en" -> {
                val local = Locale("en")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.textSelectedLanguage.text = "English"
            }
            else -> {
                val local = Locale("kk")
                Locale.setDefault(local)
                val config = resources.configuration
                config.setLocale(local)
                requireContext().resources.updateConfiguration(config, requireContext().resources.displayMetrics)
                binding.textSelectedLanguage.text = "Қазақша"
            }
        }
    }

}