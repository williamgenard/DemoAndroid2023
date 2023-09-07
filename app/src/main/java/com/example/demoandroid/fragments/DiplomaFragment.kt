package com.example.demoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.R
import com.example.demoandroid.databinding.FragmentDiplomaBinding
import com.example.demoandroid.databinding.FragmentNameBinding

class DiplomaFragment : Fragment() {

    private var _binding : FragmentDiplomaBinding? = null
    private val binding : FragmentDiplomaBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiplomaBinding.inflate(inflater, container, false)
        binding.btnGoToPassword.setOnClickListener {
            findNavController().navigate(R.id.action_diplomaFragment_to_passwordFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DiplomaFragment()
    }
}