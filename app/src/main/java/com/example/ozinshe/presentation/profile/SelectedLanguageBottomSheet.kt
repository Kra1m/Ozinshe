package com.example.ozinshe.presentation.profile

import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.BottomsheetSelectLanguageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale
import kotlin.coroutines.Continuation

class SelectedLanguageBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: BottomsheetSelectLanguageBinding
    private var languageSelectedListener: OnLanguageSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomsheetSelectLanguageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun setOnLanguageSelectedListener(listener: OnLanguageSelectedListener) {
        languageSelectedListener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val defaultLanguage: String = SharedProvider(requireContext()).getLanguage()

        when (defaultLanguage) {
            "kk" -> {
                selectedIcon(false, true, false)
            }

            "ru" -> {
                selectedIcon(false, false, true)
            }

            "en" -> {
                selectedIcon(true, false, false)
            }
        }

        binding.apply {
            btnSelectKazakh.setOnClickListener {
                selectedIcon(false, true, false)
                changeLanguage("Қазақша")
            }
            btnSelectEnglish.setOnClickListener {
                selectedIcon(true, false, false)
                changeLanguage("English")
            }
            btnSelectRussian.setOnClickListener {
                selectedIcon(false, false, true)
                changeLanguage("Русский")
            }
        }
    }


    fun changeLanguage(language: String) {
        when (language) {
            "Қазақша" -> {
                systemLanguageChange("kk")
                languageSelectedListener?.onLanguageSelected("Қазақша")
            }

            "Русский" -> {
                systemLanguageChange("ru")
                languageSelectedListener?.onLanguageSelected("Русский")
            }

            "English" -> {
                systemLanguageChange("en")
                languageSelectedListener?.onLanguageSelected("English")
            }
        }
    }

    fun systemLanguageChange(codeLanguage: String) {
        SharedProvider(requireContext()).saveLanguage(codeLanguage)

        val locale = Locale(codeLanguage)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )

        findNavController().navigate(
            R.id.profileFragment, arguments,
            NavOptions.Builder().setPopUpTo(R.id.profileFragment, true).build()
        )
    }

    fun selectedIcon(iconEnglish: Boolean, iconKazakh: Boolean, iconRussian: Boolean) {
        binding.apply {
            if (iconEnglish) {
                imgIconEnglishSelected.visibility = View.VISIBLE
            } else {
                imgIconEnglishSelected.visibility = View.GONE
            }
            if (iconKazakh) {
                imgIconKazakhSelected.visibility = View.VISIBLE
            } else {
                imgIconKazakhSelected.visibility = View.GONE
            }
            if (iconRussian) {
                imgIconRussianSelected.visibility = View.VISIBLE
            } else {
                imgIconRussianSelected.visibility = View.GONE
            }
        }
    }


}