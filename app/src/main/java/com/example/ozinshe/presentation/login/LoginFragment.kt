package com.example.ozinshe.presentation.login

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
import com.example.ozinshe.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    // main
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner){
            binding.textErrorServerExistingEmail.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            it.accessToken

            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        viewModel.errorResponse.observe(viewLifecycleOwner){
            showError(it)
        }

        binding.textInputPassword.setOnClickListener {
            togglePasswordVisibility()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.textInputEmail.text.toString()
            val password = binding.textInputPassword.text.toString()

            val emailIsValid = emailValidation(email)
            val passwordIsValid = validationPassword(password)

            if (emailIsValid && passwordIsValid){
                viewModel.loginUser(email, password)
            }else{
                validationEmail(emailIsValid)
            }
        }

        binding.btnCreateNewLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

    }

    fun togglePasswordVisibility(){
        val editTextPassword = binding.textInputPassword
        editTextPassword.transformationMethod = if (editTextPassword.transformationMethod ==
            HideReturnsTransformationMethod.getInstance()){
            PasswordTransformationMethod.getInstance()
        }else{
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
        binding.textErrorServerExistingEmail.text = "Қате орын алды"
        binding.textErrorServerExistingEmail.visibility = View.VISIBLE
    }

}