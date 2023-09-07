package com.example.demoandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.demoandroid.databinding.FragmentFirstBinding
import com.example.demoandroid.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    private val viewModel : MainViewModel by activityViewModels()

    private var _binding : FragmentSecondBinding? = null
    private val binding : FragmentSecondBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        binding.btnGoToFirst.setOnClickListener {
            viewModel.increaseCounter()
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        viewModel.actionCounter.observe(viewLifecycleOwner) {
            binding.tvCounterSecondFragment.text = it.toString()
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
            SecondFragment()
    }
}