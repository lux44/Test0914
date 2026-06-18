package com.example.test0914

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.test0914.databinding.FragmentIntentBinding

/**
 * [INTENT FRAGMENT] 인텐트 실습 화면
 * - 명시적 Intent: SecondActivity 열기 + 데이터 전달 + 결과 반환
 * - 암시적 Intent: 웹 열기, 전화 앱
 * - ContentProvider/URI: 이미지 선택
 * - ActivityResultLauncher (최신 결과 반환 방식)
 * - ViewBinding
 */
class IntentFragment: Fragment(R.layout.fragment_intent) {
    private var _binding: FragmentIntentBinding? = null
    private val binding get() = _binding!!

    private val secondLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.tvResult.text =
                    result.data?.getStringExtra("result") ?: "결과 없음"
            }
        }

    // [INTENT] [URI] [CONTENT_PROVIDER] 이미지 선택 Launcher
    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                // [CONTENT_PROVIDER] ContentResolver로 URI 접근
                val bitmap = requireContext().contentResolver
                    .openInputStream(it)
                    ?.use(BitmapFactory::decodeStream)

                binding.ivPickedImage.setImageBitmap(bitmap)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentIntentBinding.bind(view)

        // [INTENT] [EXPLICIT] 명시적 Intent - SecondActivity 열기
        binding.btnOpenSecond.setOnClickListener {
            val intent = Intent(requireContext(), SecondActivity::class.java)
            intent.putExtra("msg", binding.etMessage.text.toString())
            secondLauncher.launch(intent)
        }

        // [INTENT] [IMPLICIT] 암시적 Intent - 웹 브라우저 열기
        binding.btnOpenWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            startActivity(intent)
        }

        binding.btnDial.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"))
            startActivity(intent)
        }

        binding.btnPickImage.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}