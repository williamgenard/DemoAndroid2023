package com.example.demoandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoandroid.MainViewModel
import com.example.demoandroid.R
import com.example.demoandroid.adapter.DiplomaAdapter
import com.example.demoandroid.databinding.FragmentDiplomaBinding
import com.example.demoandroid.databinding.FragmentNameBinding

class DiplomaFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentDiplomaBinding? = null
    private val binding : FragmentDiplomaBinding
        get() = _binding!!

    private lateinit var adapter : DiplomaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiplomaBinding.inflate(inflater, container, false)
        binding.btnGoToPassword.setOnClickListener {
            findNavController().navigate(R.id.action_diplomaFragment_to_passwordFragment)
        }

        binding.btnAddDiplomaDiplomaFragment.setOnClickListener {
            viewModel.addDiploma(binding.etDiplomaDiplomaFragment.text.toString())
        }

        setRv()
        observeVm()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun observeVm() {
        viewModel.user.observe(viewLifecycleOwner) {
            val diplomas = it.diplomas
            adapter.updateDiplomas(diplomas)
        }
    }

    private fun setRv() {
        binding.rvDiplomaFragment.layoutManager = LinearLayoutManager(requireContext())
        adapter = DiplomaAdapter {
            viewModel.removeDiploma(it)
        }
        binding.rvDiplomaFragment.adapter = adapter
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