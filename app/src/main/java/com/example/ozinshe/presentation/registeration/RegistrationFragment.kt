package com.example.ozinshe.presentation.registeration

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentRegistrationBinding
import com.example.ozinshe.presentation.login.LoginViewModel
import androidx.core.content.edit
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.provideNavigationHost

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.signupResponse.observe(viewLifecycleOwner){
            binding.textErrorServerExistingEmail.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

//            val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
//            sharedPref.edit {
//                putString("accessToken", it.accessToken)
//            }
            SharedProvider(requireContext()).saveToken(it.accessToken)


            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
        }

        viewModel.errorResponse.observe(viewLifecycleOwner){
            showError(it)
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.textInputPassword.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.textInputPassword2.setOnClickListener {
            togglePasswordVisibility2()
        }

        binding.btnRegister.setOnClickListener{
            val email = binding.textInputEmail.text.toString()
            val password = binding.textInputPassword.text.toString()

            val emailIsValid = emailValidation(email)
            val passwordIsValid = validationPassword(password)

            if (emailIsValid && passwordIsValid){
                viewModel.registerUser(email, password )
            }else{
                validationEmail(emailIsValid)
            }
        }


    }

    fun togglePasswordVisibility() {
        val editTextPassword = binding.textInputPassword
        editTextPassword.transformationMethod = if (editTextPassword.transformationMethod ==
            HideReturnsTransformationMethod.getInstance()
        ) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }

    fun togglePasswordVisibility2() {
        val editTextPassword = binding.textInputPassword2
        editTextPassword.transformationMethod = if (editTextPassword.transformationMethod ==
            HideReturnsTransformationMethod.getInstance()
        ) {
            PasswordTransformationMethod.getInstance()
        } else {
            HideReturnsTransformationMethod.getInstance()
        }
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    fun emailValidation(email: String): Boolean{
        return email.trim().matches(emailPattern.toRegex())
    }
    fun validationEmail(isValid: Boolean){
        if(isValid){
            binding.textErrorLogin.error = " "
            binding.textErrorLogin.visibility = View.GONE
            binding.textInputEmail.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
        }else{
            binding.textErrorLogin.error = "Қате формат"
            binding.textErrorLogin.visibility = View.VISIBLE
            binding.textInputEmail.setBackgroundResource(R.drawable.background_edittext_12dp_error)
        }
    }


    fun validationPassword(password: String):Boolean{
        return if ( password.length<6){
            binding.textErrorServerExistingEmail.text = "Құпия сөзіңіз 6 таңбадан кем болмауы керек"
            binding.textInputPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            false
        }else{
            binding.textErrorServerExistingEmail.visibility = View.GONE
            binding.textInputPassword.setBackgroundResource(R.drawable.background_edittext_focus_action_12dp)
            true
        }
    }

    fun showError(message: String){
        binding.textErrorServerExistingEmail.text = "Мұндай email-ы бар пайдаланушы тіркелген"
        binding.textErrorServerExistingEmail.visibility = View.VISIBLE
    }


}