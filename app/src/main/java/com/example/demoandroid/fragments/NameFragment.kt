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

class NameFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentNameBinding? = null
    private val binding : FragmentNameBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNameBinding.inflate(inflater, container, false)

        binding.btnGoToDiploma.setOnClickListener {
            viewModel.setFullName(
                binding.etFirstNameNameFragment.text.toString(),
                binding.etLastNameNameFragment.text.toString()
            )
            findNavController().navigate(R.id.action_nameFragment_to_diplomaFragment)
        }

        observeVm()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun observeVm() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.etFirstNameNameFragment.setText(it.firstName)
            binding.etLastNameNameFragment.setText(it.lastName)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NameFragment()
    }
}