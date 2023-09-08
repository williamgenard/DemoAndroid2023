package com.example.demoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.MainViewModel
import com.example.demoandroid.R
import com.example.demoandroid.databinding.FragmentNameBinding
import com.example.demoandroid.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentPasswordBinding? = null
    private val binding : FragmentPasswordBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        binding.btnGoToRecap.setOnClickListener {
            val password = binding.etPasswordPasswordFragment.text.toString()
            val confirmation = binding.etConfirmationPasswordFragment.text.toString()
            if (password == confirmation) {
                viewModel.setPassword(password)
                findNavController().navigate(R.id.action_passwordFragment_to_recapFragment)
            }
        }

        observeVm()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun observeVm() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.etPasswordPasswordFragment.setText(it.password)
            binding.etConfirmationPasswordFragment.setText(it.password)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            PasswordFragment()
    }
}