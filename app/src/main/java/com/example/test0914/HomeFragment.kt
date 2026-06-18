package com.example.test0914

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentHomeBinding

class HomeFragment: Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.bind(view)

        binding.btnStartService.setOnClickListener {
            requireContext().startService(
                Intent(requireContext(), LogService::class.java)
            )
        }

        binding.btnStopService.setOnClickListener {
            requireContext().stopService(
                Intent(requireContext(), LogService::class.java)
            )
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}