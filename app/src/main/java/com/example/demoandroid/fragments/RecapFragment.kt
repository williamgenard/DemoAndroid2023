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
import com.example.demoandroid.databinding.FragmentPasswordBinding
import com.example.demoandroid.databinding.FragmentRecapBinding

class RecapFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentRecapBinding? = null
    private val binding : FragmentRecapBinding
        get() = _binding!!

    private lateinit var adapter : DiplomaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecapBinding.inflate(inflater, container, false)
        binding.btnHomeRecapFragment.setOnClickListener {
            findNavController().navigate(R.id.action_recapFragment_to_nameFragment)
        }

        binding.btnDiplomaRecapFragment.setOnClickListener {
            viewModel.addDiploma(binding.etDiplomaRecapFragment.text.toString())
        }

        observeVm()
        setRv()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setRv() {
        binding.rvDiplomaRecapFragment.layoutManager = LinearLayoutManager(requireContext())
        adapter = DiplomaAdapter {
            viewModel.removeDiploma(it)
        }
        binding.rvDiplomaRecapFragment.adapter = adapter
    }

    private fun observeVm() {
        viewModel.user.observe(viewLifecycleOwner) {
            binding.etFirstNameRecapFragment.setText(it.firstName)
            binding.etLastNameRecapFragment.setText(it.lastName)
            binding.etPasswordRecapFragment.setText(it.password)
            binding.etConfirmationRecapFragment.setText(it.password)
            adapter.updateDiplomas(it.diplomas)
        }
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