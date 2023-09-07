package com.example.demoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.R
import com.example.demoandroid.databinding.FragmentPasswordBinding
import com.example.demoandroid.databinding.FragmentRecapBinding

class RecapFragment : Fragment() {
    private var _binding : FragmentRecapBinding? = null
    private val binding : FragmentRecapBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecapBinding.inflate(inflater, container, false)
        binding.btnHomeRecapFragment.setOnClickListener {
            findNavController().navigate(R.id.action_recapFragment_to_nameFragment)
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
            RecapFragment()
    }
}