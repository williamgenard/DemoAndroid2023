package com.example.demoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.R
import com.example.demoandroid.databinding.FragmentNameBinding
import com.example.demoandroid.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {
    private var _binding : FragmentPasswordBinding? = null
    private val binding : FragmentPasswordBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        binding.btnGoToRecap.setOnClickListener {
            findNavController().navigate(R.id.action_passwordFragment_to_recapFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
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