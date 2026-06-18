package com.example.test0914

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentGraphicsBinding

/**
 * [GRAPHICS FRAGMENT] 그래픽/이미지 처리 화면
 * - 이미지 선택 (URI 기반)
 * - Canvas 변환: 확대/축소, 회전, 이동, 기울이기
 * - ColorMatrix: 회색/밝기 효과
 * - 커스텀 View (GraphicView) 사용
 * - ViewBinding
 */
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

        // [CANVAS] [SCALE] 확대
        binding.btnZoomIn.setOnClickListener {
            binding.graphicView.scaleFactor *= 1.2f
            binding.graphicView.invalidate()
        }

        // [CANVAS] [SCALE] 축소
        binding.btnZoomOut.setOnClickListener {
            binding.graphicView.scaleFactor /= 1.2f
            binding.graphicView.invalidate()
        }

        // [CANVAS] [ROTATE] 회전
        binding.btnRotate.setOnClickListener {
            binding.graphicView.rotationDeg += 15f
            binding.graphicView.invalidate()
        }

        // [CANVAS] [TRANSLATE] 이동
        binding.btnMove.setOnClickListener {
            binding.graphicView.dx += 20f
            binding.graphicView.dy += 10f
            binding.graphicView.invalidate()
        }

        // [CANVAS] [SKEW] 기울이기
        binding.btnSkew.setOnClickListener {
            binding.graphicView.skewX += 0.1f
            binding.graphicView.invalidate()
        }

        // [COLOR_MATRIX] [GRAY] 회색 효과 토글
        binding.btnGray.setOnClickListener {
            binding.graphicView.gray = !binding.graphicView.gray
            binding.graphicView.invalidate()
        }

        // [COLOR_MATRIX] [BRIGHT] 밝게
        binding.btnBright.setOnClickListener {
            binding.graphicView.brightness = 1.25f
            binding.graphicView.invalidate()
        }

        // [COLOR_MATRIX] [DARK] 어둡게
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