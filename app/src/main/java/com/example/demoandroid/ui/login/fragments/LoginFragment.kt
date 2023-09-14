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
import com.example.demoandroid.ui.login.LoginViewModel
import com.example.demoandroid.ui.login.LoginViewModelFactory
import com.example.demoandroid.ui.main.MainActivity
import com.example.demoandroid.utils.TokenManager

class LoginFragment : Fragment() {

    private val viewModel : LoginViewModel by activityViewModels { LoginViewModelFactory(requireContext()) }

    private var _binding : FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var tokenManager : TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        tokenManager = TokenManager(requireContext())

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.tvGoToRegisterLoginFragment.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLoginFragment.setOnClickListener {
            val username = binding.etUsernameLoginFragment.text.toString()
            val password = binding.etPasswordLoginFragment.text.toString()

            val loginForm = LoginForm(username, password)

            viewModel.login(loginForm)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            when (it) {
                403 -> {
                    binding.tvErrorLoginFragment.text = "La combinaison est incorrect"
                }
            }
        }

        viewModel.token.observe(viewLifecycleOwner) {
            if (it != null) {
                tokenManager.saveAuthToken(it)
                tokenManager.saveUsername(binding.etUsernameLoginFragment.text.toString())

                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        return binding.root
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            LoginFragment()
    }
}