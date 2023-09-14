package com.example.demoandroid.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.R
import com.example.demoandroid.api.models.LoginForm
import com.example.demoandroid.databinding.FragmentLoginBinding
import com.example.demoandroid.databinding.FragmentRegisterBinding
import com.example.demoandroid.ui.login.LoginViewModel
import com.example.demoandroid.ui.login.LoginViewModelFactory
import com.example.demoandroid.ui.main.MainActivity
import com.example.demoandroid.utils.TokenManager

class RegisterFragment : Fragment() {

    private val viewModel : LoginViewModel by activityViewModels { LoginViewModelFactory(requireContext()) }

    private var _binding : FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var tokenManager : TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tokenManager = TokenManager(requireContext())
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.tvGoToLoginRegisterFragment.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegisterFragment.setOnClickListener {
            val username = binding.etUsernameRegisterFragment.text.toString()
            val password = binding.etPasswordRegisterFragment.text.toString()

            val loginForm = LoginForm(username, password)

            viewModel.register(loginForm)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                403 -> {
                    binding.tvErrorFragmentRegister.text = "Le username existe déjà"
                }
            }
        }

        viewModel.token.observe(viewLifecycleOwner) {
            if (it != null) {
                tokenManager.saveAuthToken(it)
                tokenManager.saveUsername(binding.etUsernameRegisterFragment.text.toString())

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment()
    }
}