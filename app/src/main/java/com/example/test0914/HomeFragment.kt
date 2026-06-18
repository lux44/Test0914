package com.example.test0914

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.test0914.databinding.FragmentHomeBinding
import kotlin.math.log

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
        binding.btnGoDiary.setOnClickListener {
            Log.i("btn", "go diary click")
            MainActivity.binding.bottomNav.selectedItemId =
                R.id.diaryFragment
        }
        binding.btnGoGraphics.setOnClickListener {
            Log.i("btn", "go graphic click")
            MainActivity.binding.bottomNav.selectedItemId = R.id.graphicsFragment
        }
        Log.e("Tag", "home fragment")
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}