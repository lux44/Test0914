package com.example.test0914

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentGraphicsBinding

class GraphicsFragment: Fragment(R.layout.fragment_graphics) {
    private var _binding: FragmentGraphicsBinding? = null
    private val binding get() = _binding!!

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                val bitmap = requireContext().contentResolver
                    .openInputStream(it)
                    ?.use(BitmapFactory::decodeStream)
                bitmap?.let { bmp -> binding.graphicView.setBitmap(bmp) }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentGraphicsBinding.bind(view)

        binding.btnPickImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        binding.btnZoomIn.setOnClickListener {
            binding.graphicView.scaleFactor *= 1.2f
            binding.graphicView.invalidate()
        }

        binding.btnZoomOut.setOnClickListener {
            binding.graphicView.scaleFactor /= 1.2f
            binding.graphicView.invalidate()
        }

        binding.btnRotate.setOnClickListener {
            binding.graphicView.rotationDeg += 15f
            binding.graphicView.invalidate()
        }

        binding.btnMove.setOnClickListener {
            binding.graphicView.dx += 20f
            binding.graphicView.dy += 10f
            binding.graphicView.invalidate()
        }

        binding.btnSkew.setOnClickListener {
            binding.graphicView.skewX += 0.1f
            binding.graphicView.invalidate()
        }

        binding.btnGray.setOnClickListener {
            binding.graphicView.gray = !binding.graphicView.gray
            binding.graphicView.invalidate()
        }

        binding.btnBright.setOnClickListener {
            binding.graphicView.brightness = 1.25f
            binding.graphicView.invalidate()
        }

        binding.btnDark.setOnClickListener {
            binding.graphicView.brightness = 0.75f
            binding.graphicView.invalidate()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}